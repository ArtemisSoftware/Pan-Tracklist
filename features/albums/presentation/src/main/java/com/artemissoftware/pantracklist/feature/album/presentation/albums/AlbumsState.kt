package com.artemissoftware.pantracklist.feature.album.presentation.albums

import androidx.paging.PagingData
import com.artemissoftware.pantracklist.album.domain.models.Album
import com.artemissoftware.pantracklist.core.presentation.models.ErrorData
import kotlinx.coroutines.flow.Flow

internal data class AlbumsState(
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val albums: Flow<PagingData<Album>>? = null,
    val error: ErrorData? = null
)
