package com.artemissoftware.pantracklist.domain.repository

import com.artemissoftware.pantracklist.core.domain.Resource
import com.artemissoftware.pantracklist.domain.models.Album

interface LeboncoinRepository {
    suspend fun downloadAlbums(): Resource<Unit>
}