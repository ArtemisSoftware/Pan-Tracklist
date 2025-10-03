package com.artemissoftware.pantracklist.albums.data.mapper

import com.artemissoftware.pantracklist.album.domain.models.Album
import com.artemissoftware.pantracklist.albums.data.network.dto.AlbumDto
import com.artemissoftware.pantracklist.database.entities.AlbumEntity

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