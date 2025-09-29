package com.artemissoftware.pantracklist.data.network.source

import com.artemissoftware.pantracklist.core.domain.Resource
import com.artemissoftware.pantracklist.data.network.LeboncoinApi
import com.artemissoftware.pantracklist.data.network.dto.AlbumDto
import com.artemissoftware.pantracklist.data.network.safeCall

class LeboncoinApiSource constructor(private val leboncoinApi: LeboncoinApi) {

    suspend fun getAlbums(): Resource<List<AlbumDto>> {
        return safeCall { leboncoinApi.getAlbums() }
    }
}