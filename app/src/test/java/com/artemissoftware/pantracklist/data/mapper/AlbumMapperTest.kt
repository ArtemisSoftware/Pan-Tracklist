package com.artemissoftware.pantracklist.data.mapper

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.artemissoftware.pantracklist.features.albums.data.mapper.toAlbum
import com.artemissoftware.pantracklist.features.albums.data.mapper.toEntity
import com.artemissoftware.pantracklist.util.TestData.albumList
import com.artemissoftware.pantracklist.util.TestData.albumListDto
import com.artemissoftware.pantracklist.util.TestData.albumListEntities
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