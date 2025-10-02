package com.artemissoftware.pantracklist.features.albums.presentation.albums

internal sealed interface AlbumsEvent {
    data object ReLoadAlbums: AlbumsEvent
    data object Refresh: AlbumsEvent
}