package com.artemissoftware.pantracklist.features.albums.data.repository

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.artemissoftware.pantracklist.core.domain.Resource
import com.artemissoftware.pantracklist.core.domain.error.DataError
import com.artemissoftware.pantracklist.features.albums.data.network.source.LeboncoinApiSource
import com.artemissoftware.pantracklist.util.server.LeboncoinMockWebServer
import com.artemissoftware.pantracklist.util.server.ServerData.ALBUMS_RESPONSE
import com.artemissoftware.pantracklist.util.server.ServerData.ERROR_RESPONSE
import com.artemissoftware.pantracklist.util.TestData
import com.artemissoftware.pantracklist.util.server.enqueueResponse
import com.artemissoftware.pantracklist.util.fake.FakeAlbumDao
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LeboncoinRepositoryImplTest {

    private lateinit var albumDao: FakeAlbumDao
    private val mockWebServer = MockWebServer()
    private lateinit var leboncoinApiSource: LeboncoinApiSource
    private lateinit var leboncoinRepository: LeboncoinRepositoryImpl

    @BeforeEach
    fun setUp(){

        albumDao = FakeAlbumDao()
        leboncoinApiSource = LeboncoinApiSource(LeboncoinMockWebServer.getApi(mockWebServer))
        leboncoinRepository = LeboncoinRepositoryImpl(
            leboncoinApiSource = leboncoinApiSource,
            albumDao = albumDao
        )
    }

    @AfterEach
    fun tearDown(){
        mockWebServer.shutdown()
    }

    @Test
    fun `downloadAlbums should return success when API call succeeds and database insertion is successful`() = runTest {
        mockWebServer.enqueueResponse(ALBUMS_RESPONSE)

        val result = leboncoinRepository.downloadAlbums()

        assertThat(result)
            .isInstanceOf(Resource.Success::class)

        assertThat(albumDao.getCount())
            .isEqualTo(TestData.albumList.size)
    }

    @Test
    fun `downloadAlbums should return failure when API call fails`() = runTest {
        mockWebServer.enqueueResponse(ERROR_RESPONSE, 400)

        val result = leboncoinRepository.downloadAlbums()

        assertThat(result)
            .isInstanceOf(Resource.Failure::class)
    }

    @Test
    fun `downloadAlbums should return failure when database insert fails`() = runTest {
        mockWebServer.enqueueResponse(ALBUMS_RESPONSE)

        albumDao.throwInsertException = true

        val result = leboncoinRepository.downloadAlbums()

        assertThat(result)
            .isInstanceOf(Resource.Failure::class)

        assertThat((result as Resource.Failure).error)
            .isEqualTo(DataError.Local.DISK_FULL)
    }

    @Test
    fun `downloadAlbums, try to load again with failure should keep existing list from database`() = runTest {
        mockWebServer.enqueueResponse(ALBUMS_RESPONSE)

        val result = leboncoinRepository.downloadAlbums()

        assertThat(result)
            .isInstanceOf(Resource.Success::class)

        assertThat(albumDao.getCount())
            .isEqualTo(TestData.albumList.size)

        mockWebServer.enqueueResponse(ERROR_RESPONSE, 400)

        val result2 = leboncoinRepository.downloadAlbums()

        assertThat(result2)
            .isInstanceOf(Resource.Success::class)

        assertThat(albumDao.getCount())
            .isEqualTo(TestData.albumList.size)
    }

    @Test
    fun `downloadAlbums, try to reload again with failure should keep existing list from database`() = runTest {
        mockWebServer.enqueueResponse(ALBUMS_RESPONSE)

        val result = leboncoinRepository.downloadAlbums()

        assertThat(result)
            .isInstanceOf(Resource.Success::class)

        assertThat(albumDao.getCount())
            .isEqualTo(TestData.albumList.size)

        mockWebServer.enqueueResponse(ERROR_RESPONSE, 400)

        val result2 = leboncoinRepository.downloadAlbums(forceReload = true)

        assertThat(result2)
            .isInstanceOf(Resource.Failure::class)

        assertThat(albumDao.getCount())
            .isEqualTo(TestData.albumList.size)
    }
}