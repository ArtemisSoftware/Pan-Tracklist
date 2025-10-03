package com.artemissoftware.pantracklist.core.presentation.composables.pagination

import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

@Composable
fun <T : Any>PaginationContent(
    items: LazyPagingItems<T>,
    content: @Composable (LazyPagingItems<T>) -> Unit,
    loadingContent: @Composable () -> Unit = {}
) {
    items.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }
        when {
            loadState.refresh is LoadState.Loading -> {
                loadingContent()
            }

            error != null && this.itemCount > 0 -> {
                content(items)
            }

            error != null -> Unit

            else -> {
                content(items)
            }
        }
    }
}
