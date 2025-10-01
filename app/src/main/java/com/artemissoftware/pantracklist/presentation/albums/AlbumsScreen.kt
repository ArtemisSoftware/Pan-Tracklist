package com.artemissoftware.pantracklist.presentation.albums

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.artemissoftware.pantracklist.core.designsystem.theme.PanTracklistTheme
import com.artemissoftware.pantracklist.core.designsystem.theme.spacing
import com.artemissoftware.pantracklist.core.presentation.composables.pagination.PaginationContent
import com.artemissoftware.pantracklist.core.presentation.composables.scaffold.PTScaffold
import com.artemissoftware.pantracklist.presentation.albums.composables.AlbumList

@Composable
internal fun AlbumsScreen(
    viewModel: AlbumsViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value

    AlbumsScreenContent(
        state = state,
    )
}

@Composable
private fun AlbumsScreenContent(
    state: AlbumsState,
) {

    val lazyListState = rememberLazyListState()

    PTScaffold(
        isLoading = state.isLoading,
        error = state.error,
        content = {

            state.albums?.let {

                val item = it.collectAsLazyPagingItems()

                PaginationContent(
                    items = item,
                    loadingContent = {
                        /*
                        ShimmerCatalogGrid(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = MaterialTheme.spacing.spacing3)
                        )
                        */
                    },
                    content = { entries ->
                        AlbumList(
                            state = lazyListState,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = MaterialTheme.spacing.spacing3),
                            entries = entries,
                        )
                    }
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun AlbumsScreenContentPreview() {
    PanTracklistTheme {
        AlbumsScreenContent(
            state = AlbumsState(),
        )
    }
}