package com.artemissoftware.pantracklist.presentation.albums

internal sealed interface AlbumsEvent {
    data object LoadAlbums: AlbumsEvent
}