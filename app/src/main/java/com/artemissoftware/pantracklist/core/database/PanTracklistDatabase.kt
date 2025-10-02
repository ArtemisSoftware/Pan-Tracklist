package com.artemissoftware.pantracklist.core.database


import androidx.room.Database
import androidx.room.RoomDatabase
import com.artemissoftware.pantracklist.core.database.dao.AlbumDao
import com.artemissoftware.pantracklist.core.database.entities.AlbumEntity

@Database(
    entities = [
        AlbumEntity::class,
    ],
    version = 1,
)
abstract class PanTracklistDatabase : RoomDatabase() {

    abstract fun getAlbumDao(): AlbumDao

    companion object {
        const val DATABASE_NAME = "pan_tracklist_db"
    }
}