package com.artemissoftware.pantracklist.album.domain.repository

import androidx.paging.PagingData
import com.artemissoftware.pantracklist.album.domain.models.Album
import com.artemissoftware.pantracklist.domain.Resource
import kotlinx.coroutines.flow.Flow

interface LeboncoinRepository {
    suspend fun downloadAlbums(forceReload: Boolean = false): Resource<Unit>
    fun getAlbums(): Flow<PagingData<Album>>
}