package com.artemissoftware.pantracklist.database

import com.artemissoftware.pantracklist.database.entities.AlbumEntity


object TestData {
    val albumListEntities = listOf(
        AlbumEntity(
            albumId = 1,
            id = 1,
            title = "The flowers",
            url = "https://placehold.co/600x600/92c952/white/png",
            thumbnailUrl = "https://placehold.co/150x150/92c952/white/png"
        ),
        AlbumEntity(
            albumId = 1,
            id = 2,
            title = "The flowers are red",
            url = "https://placehold.co/600x600/92c953/white/png",
            thumbnailUrl = "https://placehold.co/150x150/92c953/white/png"
        )
    )
}