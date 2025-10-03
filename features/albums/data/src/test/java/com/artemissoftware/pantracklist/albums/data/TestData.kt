package com.artemissoftware.pantracklist.albums.data

import com.artemissoftware.pantracklist.album.domain.models.Album
import com.artemissoftware.pantracklist.albums.data.network.dto.AlbumDto
import com.artemissoftware.pantracklist.database.entities.AlbumEntity

object TestData {

    val albumListDto = listOf(
        AlbumDto(
            albumId = 1,
            id = 1,
            title = "The flowers",
            url = "https://placehold.co/600x600/92c952/white/png",
            thumbnailUrl = "https://placehold.co/150x150/92c952/white/png"
        ),
        AlbumDto(
            albumId = 1,
            id = 2,
            title = "The flowers are red",
            url = "https://placehold.co/600x600/92c953/white/png",
            thumbnailUrl = "https://placehold.co/150x150/92c953/white/png"
        )
    )

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