package com.artemissoftware.pantracklist.presentation.util

internal object TestTags {

    private const val ALBUM = "album_"
    private const val ALBUM_CARD =  ALBUM + "card"
    fun getAlbumCardTag(description: String) = ALBUM_CARD + description

    const val ALBUM_CARD_CONTENT = ALBUM + "card_content"
    const val ALBUM_CARD_IMAGE = ALBUM + "image"
    const val ALBUM_CARD_NAME = ALBUM + "name"
}