package com.artemissoftware.pantracklist.features.albums.data.mapper

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.artemissoftware.pantracklist.features.albums.data.mapper.toAlbum
import com.artemissoftware.pantracklist.features.albums.data.mapper.toEntity
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