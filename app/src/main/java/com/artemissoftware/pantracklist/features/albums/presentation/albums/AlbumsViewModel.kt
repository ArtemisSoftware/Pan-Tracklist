package com.artemissoftware.pantracklist.features.albums.presentation.albums

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.artemissoftware.pantracklist.R
import com.artemissoftware.pantracklist.core.presentation.composables.text.UiText
import com.artemissoftware.pantracklist.core.presentation.models.ErrorData
import com.artemissoftware.pantracklist.core.presentation.util.extensions.toUiText
import com.artemissoftware.pantracklist.features.albums.domain.repository.LeboncoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class AlbumsViewModel @Inject constructor(
    private val leboncoinRepository: LeboncoinRepository
) : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(AlbumsState())
    val state = _state.asStateFlow()
        .onStart {
            if (!hasLoadedInitialData) {
                getAlbums()
                loadAlbums()
                hasLoadedInitialData = true
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = AlbumsState()
        )

    fun onTriggerEvent(event: AlbumsEvent){
        when(event){
            AlbumsEvent.ReLoadAlbums -> loadAlbums(forceReload = true)
            AlbumsEvent.Refresh -> refreshAlbums()
        }
    }

    private fun loadAlbums(forceReload: Boolean = false) = with(_state){

        update { it.copy(isLoading = !value.isRefreshing, error = null) }

        viewModelScope.launch {
            leboncoinRepository.downloadAlbums(forceReload)
                .onSuccess {
                    update {
                        it.copy(isLoading = false, error = null, isRefreshing = false)
                    }
                }
                .onFailure { error ->
                    update {
                        it.copy(
                            isLoading = false,
                            isRefreshing = false,
                            error = ErrorData(
                                message = error.toUiText(),
                                buttonText = UiText.StringResource(R.string.try_again),
                                onClick = {
                                    onTriggerEvent(AlbumsEvent.ReLoadAlbums)
                                }
                            ),
                        )
                    }
                }
        }
    }

    private fun refreshAlbums() = with(_state){
        update { it.copy(isRefreshing = true) }
        loadAlbums(forceReload = true)
    }

    private fun getAlbums() = with(_state) {
        val albums = leboncoinRepository.getAlbums()
            .cachedIn(viewModelScope)

        update {
            it.copy(albums = albums)
        }
    }
}