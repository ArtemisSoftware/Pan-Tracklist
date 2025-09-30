package com.artemissoftware.pantracklist.presentation.albums

sealed interface AlbumsEvent {
    data object LoadAlbums: AlbumsEvent
}