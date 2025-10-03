package com.artemissoftware.pantracklist.features.albums.data.network

import com.artemissoftware.pantracklist.features.albums.data.network.dto.AlbumDto
import retrofit2.http.GET

interface LeboncoinApi {

    @GET("img/shared/technical-test.json")
    suspend fun getAlbums(): List<AlbumDto>
}