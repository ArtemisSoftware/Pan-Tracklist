package com.artemissoftware.pantracklist.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.artemissoftware.pantracklist.data.database.entities.AlbumEntity

@Dao
interface AlbumDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(albums: List<AlbumEntity>)

    @Query("DELETE FROM album")
    suspend fun deleteAll()

    @Transaction
    suspend fun reload(albums: List<AlbumEntity>){
        deleteAll()
        insert(albums)
    }

    @Query("SELECT * FROM album")
    fun getAlbums(): PagingSource<Int, AlbumEntity>


    @Query("SELECT COUNT(*) FROM album")
    suspend fun getCount(): Int
}