package com.artemissoftware.pantracklist.data.network.source

import com.artemissoftware.pantracklist.data.network.HandleApi
import com.artemissoftware.pantracklist.data.network.LeboncoinApi
import com.artemissoftware.pantracklist.data.network.dto.AlbumDto
import javax.inject.Inject

class LeboncoinApiSource @Inject constructor(private val leboncoinApi: LeboncoinApi) {

    suspend fun getAlbums(): List<AlbumDto> {
        return HandleApi.safeApiCall { leboncoinApi.getAlbums() }
    }
}