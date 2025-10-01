package com.artemissoftware.pantracklist.presentation.albums

import androidx.paging.PagingData
import com.artemissoftware.pantracklist.core.presentation.models.ErrorData
import com.artemissoftware.pantracklist.domain.models.Album
import kotlinx.coroutines.flow.Flow

internal data class AlbumsState(
    val isLoading: Boolean = false,
    val albums: Flow<PagingData<Album>>? = null,
    val error: ErrorData? = null
)
