package com.artemissoftware.pantracklist.feature.album.presentation

import com.artemissoftware.pantracklist.album.domain.models.Album

object TestInstrumentedData {

    val albumList = listOf(
        Album(
            id = 1,
            title = "The flowers",
            thumbnailUrl = "https://placehold.co/150x150/92c952/white/png"
        ),
        Album(
            id = 2,
            title = "The flowers are red",
            thumbnailUrl = "https://placehold.co/150x150/92c953/white/png"
        )
    )
}