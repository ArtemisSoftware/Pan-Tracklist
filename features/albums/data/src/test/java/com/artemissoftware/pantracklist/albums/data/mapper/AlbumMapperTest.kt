package com.artemissoftware.pantracklist.albums.data.mapper

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.artemissoftware.pantracklist.albums.data.TestData.albumList
import com.artemissoftware.pantracklist.albums.data.TestData.albumListDto
import com.artemissoftware.pantracklist.albums.data.TestData.albumListEntities
import org.junit.jupiter.api.Test

class AlbumMapperTest {

    @Test
    fun `Map AlbumDto to AlbumEntity`(){
        assertThat(albumListDto.first().toEntity())
            .isEqualTo(albumListEntities.first())
    }

    @Test
    fun `Map AlbumEntity to Album`(){
        assertThat(albumListEntities.first().toAlbum())
            .isEqualTo(albumList.first())
    }
}