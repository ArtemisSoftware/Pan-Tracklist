package com.artemissoftware.pantracklist.albums.data.util.fake

import android.database.sqlite.SQLiteException
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.artemissoftware.pantracklist.database.dao.AlbumDao
import com.artemissoftware.pantracklist.database.entities.AlbumEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf

class FakeAlbumDao(): AlbumDao {

    private val items = mutableListOf<AlbumEntity>()
    var throwInsertException = false

    override suspend fun insert(albums: List<AlbumEntity>) {

        if(throwInsertException)
            throw SQLiteException("Disk full")

        items.addAll(albums)
    }

    override suspend fun deleteAll() {
        items.clear()
    }

    override fun getAlbums(): PagingSource<Int, AlbumEntity> {
        return FakeAlbumPagingSource(items)
    }

    override suspend fun getCount(): Int {
        return items.size
    }
}

private class FakeAlbumPagingSource(
    private val data: List<AlbumEntity>
) : PagingSource<Int, AlbumEntity>() {

    override fun getRefreshKey(state: PagingState<Int, AlbumEntity>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AlbumEntity> {
        val page = params.key ?: 1
        val pageSize = params.loadSize

        val fromIndex = (page - 1) * pageSize
        val toIndex = (fromIndex + pageSize).coerceAtMost(data.size)

        val pageData = if (fromIndex < data.size) {
            data.subList(fromIndex, toIndex)
        } else {
            emptyList()
        }

        return LoadResult.Page(
            data = pageData,
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (toIndex >= data.size) null else page + 1
        )
    }
}