package com.artemissoftware.pantracklist.albums.data.network.source

import com.artemissoftware.pantracklist.albums.data.network.LeboncoinApi
import com.artemissoftware.pantracklist.albums.data.network.dto.AlbumDto
import com.artemissoftware.pantracklist.domain.Resource
import com.artemissoftware.pantracklist.network.HandleApi
import javax.inject.Inject

class LeboncoinApiSource @Inject constructor(private val leboncoinApi: LeboncoinApi) {

    suspend fun getAlbums(): Resource<List<AlbumDto>> {
        return HandleApi.safeApiCall { leboncoinApi.getAlbums() }
    }
}