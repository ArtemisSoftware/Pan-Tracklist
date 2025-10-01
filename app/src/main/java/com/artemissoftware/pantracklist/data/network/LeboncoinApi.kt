package com.artemissoftware.pantracklist.data.network

import com.artemissoftware.pantracklist.data.network.dto.AlbumDto
import retrofit2.http.GET

interface LeboncoinApi {

    @GET("img/shared/technical-test.json")
    suspend fun getAlbums(): List<AlbumDto>

    companion object { //TODO: rever isto
        const val BASE_URL = "https://static.leboncoin.fr/"
    }
}