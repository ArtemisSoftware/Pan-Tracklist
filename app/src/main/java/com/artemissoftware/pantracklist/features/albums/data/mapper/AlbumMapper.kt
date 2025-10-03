package com.artemissoftware.pantracklist.features.albums.data.mapper

import com.artemissoftware.pantracklist.database.entities.AlbumEntity
import com.artemissoftware.pantracklist.features.albums.data.network.dto.AlbumDto
import com.artemissoftware.pantracklist.features.albums.domain.models.Album

internal fun AlbumDto.toEntity(): AlbumEntity{
    return AlbumEntity(
        id = id,
        albumId = albumId,
        title = title,
        thumbnailUrl = thumbnailUrl,
        url = url
    )
}

internal fun AlbumEntity.toAlbum(): Album{
    return Album(
        id = id,
        thumbnailUrl = thumbnailUrl,
        title = title
    )
}