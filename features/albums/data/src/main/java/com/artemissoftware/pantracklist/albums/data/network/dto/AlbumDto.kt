package com.artemissoftware.pantracklist.albums.data.network.dto


import com.squareup.moshi.Json

data class AlbumDto(
    @field:Json(name = "albumId")
    val albumId: Int,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "thumbnailUrl")
    val thumbnailUrl: String,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "url")
    val url: String
)