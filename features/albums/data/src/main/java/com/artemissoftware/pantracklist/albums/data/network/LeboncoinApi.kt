package com.artemissoftware.pantracklist.albums.data.network

import com.artemissoftware.pantracklist.albums.data.network.dto.AlbumDto
import retrofit2.http.GET

interface LeboncoinApi {

    @GET("img/shared/technical-test.json")
    suspend fun getAlbums(): List<AlbumDto>
}