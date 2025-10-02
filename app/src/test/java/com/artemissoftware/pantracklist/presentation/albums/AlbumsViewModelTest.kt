package com.artemissoftware.pantracklist.presentation.albums

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isNotNull
import assertk.assertions.isNull
import assertk.assertions.isTrue
import com.artemissoftware.pantracklist.util.extensions.MainCoroutineExtension
import com.artemissoftware.pantracklist.util.fake.FakeLeboncoinRepository
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineExtension::class)
class AlbumsViewModelTest {

    private lateinit var leboncoinRepository: FakeLeboncoinRepository
    private lateinit var viewModel: AlbumsViewModel

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
        }
    }
}