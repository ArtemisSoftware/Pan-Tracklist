package com.artemissoftware.pantracklist.presentation.albums.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.artemissoftware.pantracklist.domain.models.Album

@Composable
internal fun AlbumList(
    state: LazyListState,
    entries: LazyPagingItems<Album>,
    modifier: Modifier = Modifier,
    reloadContent: @Composable () -> Unit = {},
) {
    LazyColumn (
        state = state,
        modifier = modifier,
    ) {
        items(
            count = entries.itemCount,
            key = entries.itemKey { it.id },
            contentType = entries.itemContentType { "album" },
        ) {
            entries[it]?.let { entry ->
                AlbumCard(
                    album = entry,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}