package com.artemissoftware.pantracklist.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "album")
data class AlbumEntity(
    @PrimaryKey
    val id: Int,
    val albumId: Int,
    val title: String,
    val thumbnailUrl: String,
    val url: String
)
