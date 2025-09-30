package com.artemissoftware.pantracklist.domain.repository

import com.artemissoftware.pantracklist.core.domain.Resource

interface LeboncoinRepository {
    suspend fun downloadAlbums(): Resource<Unit>
}