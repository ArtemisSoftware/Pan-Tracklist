package com.artemissoftware.pantracklist.data.source

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.artemissoftware.pantracklist.core.domain.Resource
import com.artemissoftware.pantracklist.data.network.LeboncoinApi
import com.artemissoftware.pantracklist.data.network.source.LeboncoinApiSource
import com.artemissoftware.pantracklist.util.ServerData.ALBUMS_RESPONSE
import com.artemissoftware.pantracklist.util.ServerData.ERROR_ALBUMS_WITH_MISSING_FIELDS_RESPONSE
import com.artemissoftware.pantracklist.util.ServerData.ERROR_RESPONSE
import com.artemissoftware.pantracklist.util.TestData.albumListDto
import com.artemissoftware.pantracklist.util.enqueueResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class LeboncoinApiSourceTest {

    private val mockWebServer = MockWebServer()
    private val client = OkHttpClient.Builder().build()
    private lateinit var leboncoinApi: LeboncoinApi
    private lateinit var leboncoinApiSource: LeboncoinApiSource

    @BeforeEach
    fun setUp(){
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        leboncoinApi = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(LeboncoinApi::class.java)

        leboncoinApiSource = LeboncoinApiSource(leboncoinApi = leboncoinApi)
    }

    @AfterEach
    fun tearDown(){
        mockWebServer.shutdown()
    }

    @Test
    fun `Get albums`() = runTest {
        mockWebServer.enqueueResponse(ALBUMS_RESPONSE)

        val result = leboncoinApiSource.getAlbums()

        assertThat(result)
            .isEqualTo(albumListDto)
    }

    @Test
    fun `Get albums, return error`() = runTest {
        mockWebServer.enqueueResponse(ERROR_RESPONSE, 400)

        assertFailure {
            leboncoinApiSource.getAlbums()
        }
    }

    @Test
    fun `Get albums, api responds with incomplete json, return error`() = runTest { //TODO: meter tudo como nullable e rever o tratamento desta exepcao
        mockWebServer.enqueueResponse(ERROR_ALBUMS_WITH_MISSING_FIELDS_RESPONSE, 200)

        assertFailure {
            leboncoinApiSource.getAlbums()
        }
    }
}