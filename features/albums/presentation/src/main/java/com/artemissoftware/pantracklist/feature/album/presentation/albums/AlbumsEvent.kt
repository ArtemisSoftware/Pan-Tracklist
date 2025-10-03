package com.artemissoftware.pantracklist.feature.album.presentation.albums

internal sealed interface AlbumsEvent {
    data object ReLoadAlbums: AlbumsEvent
    data object Refresh: AlbumsEvent
}