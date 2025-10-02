package com.artemissoftware.pantracklist.util.server

import com.artemissoftware.pantracklist.features.albums.data.network.LeboncoinApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object LeboncoinMockWebServer {

    fun getApi(mockWebServer: MockWebServer): LeboncoinApi {
        val client = OkHttpClient.Builder().build()

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(LeboncoinApi::class.java)
    }
}