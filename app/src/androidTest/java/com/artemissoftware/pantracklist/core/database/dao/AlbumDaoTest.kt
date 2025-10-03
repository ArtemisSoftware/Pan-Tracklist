package com.artemissoftware.pantracklist.core.database.dao

import androidx.paging.PagingSource
import androidx.test.ext.junit.runners.AndroidJUnit4
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.artemissoftware.pantracklist.core.database.PanTracklistDatabase
import com.artemissoftware.pantracklist.util.TestInstrumentedData.albumListEntities
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class AlbumDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var panTracklistDatabase: PanTracklistDatabase

    lateinit var albumDao: AlbumDao

    @Before
    fun setUp() {
        hiltRule.inject()
        albumDao = panTracklistDatabase.getAlbumDao()
    }

    @After
    fun tearDown() {
        panTracklistDatabase.close()
    }


    @Test
    fun `Insert Albums`() = runBlocking {
        albumDao.insert(albumListEntities)

        val result = albumDao.getCount()

        assertThat(result)
            .isEqualTo(albumListEntities.size)
    }

    @Test
    fun `Get all products`() = runBlocking  {

        albumDao.insert(albumListEntities)
        val pagingSource = albumDao.getAlbums()

        val result = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        assertThat(result)
            .isInstanceOf(PagingSource.LoadResult.Page::class)

        assertThat((result as PagingSource.LoadResult.Page).data.size)
            .isEqualTo(albumListEntities.size)
        assertThat(albumListEntities)
            .isEqualTo(result.data)
    }

    @Test
    fun `Reload Albums twice with different values`() = runBlocking {
        albumDao.reload(albumListEntities)

        val result = albumDao.getCount()

        assertThat(result)
            .isEqualTo(albumListEntities.size)

        val newList = albumListEntities.drop(1)

        albumDao.reload(newList)

        val pagingSource = albumDao.getAlbums()
        val result2 = pagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        assertThat(result2)
            .isInstanceOf(PagingSource.LoadResult.Page::class)

        assertThat((result2 as PagingSource.LoadResult.Page).data.size)
            .isEqualTo(newList.size)

        assertThat(newList)
            .isEqualTo(result2.data)
    }

}