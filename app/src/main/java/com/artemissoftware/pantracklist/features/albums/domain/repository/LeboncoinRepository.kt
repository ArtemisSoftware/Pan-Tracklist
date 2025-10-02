package com.artemissoftware.pantracklist.features.albums.domain.repository

import androidx.paging.PagingData
import com.artemissoftware.pantracklist.core.domain.Resource
import com.artemissoftware.pantracklist.features.albums.domain.models.Album
import kotlinx.coroutines.flow.Flow

interface LeboncoinRepository {
    suspend fun downloadAlbums(forceReload: Boolean = false): Resource<Unit>
    fun getAlbums(): Flow<PagingData<Album>>
}