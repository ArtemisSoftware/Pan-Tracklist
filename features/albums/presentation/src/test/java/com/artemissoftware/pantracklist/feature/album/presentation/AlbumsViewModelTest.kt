package com.artemissoftware.pantracklist.feature.album.presentation

import androidx.paging.AsyncPagingDataDiffer
import androidx.paging.PagingData
import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import com.artemissoftware.pantracklist.album.domain.models.Album
import com.artemissoftware.pantracklist.feature.album.presentation.albums.AlbumsViewModel
import com.artemissoftware.pantracklist.feature.album.presentation.util.TestAlbumDiffer
import com.artemissoftware.pantracklist.feature.album.presentation.util.TestData
import com.artemissoftware.pantracklist.feature.album.presentation.util.extension.MainCoroutineExtension
import com.artemissoftware.pantracklist.feature.album.presentation.util.fake.FakeLeboncoinRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MainCoroutineExtension::class)
class AlbumsViewModelTest {

    private lateinit var leboncoinRepository: FakeLeboncoinRepository
    private lateinit var viewModel: AlbumsViewModel


    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @BeforeEach
    fun setUp() {
        leboncoinRepository = FakeLeboncoinRepository()
        viewModel = AlbumsViewModel(leboncoinRepository = leboncoinRepository)
    }

    @Test
    fun `Download all albums with success`() = runTest {

        viewModel.state.test {

            val initial = awaitItem()
            assertThat(initial.isLoading).isFalse()
            assertThat(initial.albums).isNull()

            val loading = awaitItem()
            assertThat(loading.isLoading).isTrue()

            val loaded = awaitItem()
            assertThat(loaded.isLoading).isFalse()
            assertThat(loaded.error).isNull()
            assertThat(loaded.albums).isNotNull()

            val differ = getDataFromPaging(loaded.albums!!)

            advanceUntilIdle()

            val actualAlbums = differ.snapshot().items
            assertThat(actualAlbums).isEqualTo(TestData.albumList)
        }
    }

    @Test
    fun `Try Download all albums but have data in data base do not download`() = runTest {

        leboncoinRepository.hasInitialData = true

        viewModel.state.test {

            val initial = awaitItem()
            assertThat(initial.isLoading).isFalse()
            assertThat(initial.albums).isNull()

            val loaded = awaitItem()
            assertThat(loaded.isLoading).isTrue()
            assertThat(loaded.error).isNull()
            assertThat(loaded.albums).isNotNull()

            val differ = getDataFromPaging(loaded.albums!!)

            advanceUntilIdle()

            val actualAlbums = differ.snapshot().items
            assertThat(actualAlbums).isEqualTo(TestData.albumList)

            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `Download all albums return error`() = runTest {

        leboncoinRepository.errorLoadingData = true

        viewModel.state.test {

            val initial = awaitItem()
            assertThat(initial.isLoading).isFalse()

            val loading = awaitItem()
            assertThat(loading.isLoading).isTrue()

            val loadedWithError = awaitItem()
            assertThat(loadedWithError.isLoading).isFalse()
            assertThat(loadedWithError.error).isNotNull()
            assertThat(loadedWithError.albums).isNotNull()

            val differ = getDataFromPaging(loadedWithError.albums!!)

            advanceUntilIdle()

            val actualAlbums = differ.snapshot().items
            assertThat(actualAlbums).hasSize(0)
        }
    }

    private suspend fun getDataFromPaging(data: Flow<PagingData<Album>>): AsyncPagingDataDiffer<Album> {
        val repoPagingData = data.first()
        val differ = TestAlbumDiffer(testDispatcher).differ
        differ.submitData(repoPagingData)
        return differ
    }
}