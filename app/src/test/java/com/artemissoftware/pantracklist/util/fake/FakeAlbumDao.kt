package com.artemissoftware.pantracklist.util.fake

import android.database.sqlite.SQLiteException
import androidx.paging.PagingSource
import com.artemissoftware.pantracklist.data.database.dao.AlbumDao
import com.artemissoftware.pantracklist.data.database.entities.AlbumEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first

class FakeAlbumDao(): AlbumDao {

    private val items = MutableStateFlow<List<AlbumEntity>>(emptyList())
    var throwInsertException = false

    override suspend fun insert(albums: List<AlbumEntity>) {

        if(throwInsertException)
            throw SQLiteException("Disk full")

        val list = items.value.toMutableList()
        list.addAll(albums)
        items.value = list
    }

    override suspend fun deleteAll() {
        items.value = emptyList()
    }

    override fun getAlbums(): PagingSource<Int, AlbumEntity> {
        TODO("Not yet implemented")
    }

    override suspend fun getCount(): Int {
        return items.first().size
    }
}