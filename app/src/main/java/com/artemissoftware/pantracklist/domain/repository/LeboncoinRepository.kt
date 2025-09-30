package com.artemissoftware.pantracklist.domain.repository

import androidx.paging.PagingData
import com.artemissoftware.pantracklist.core.domain.Resource
import com.artemissoftware.pantracklist.domain.models.Album
import kotlinx.coroutines.flow.Flow

interface LeboncoinRepository {
    suspend fun downloadAlbums(): Resource<Unit>
    fun getAlbums(): Flow<PagingData<Album>>
}