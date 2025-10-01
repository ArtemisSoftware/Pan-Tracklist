package com.artemissoftware.pantracklist.core.presentation.composables.pagination

import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems

@Composable
fun <T : Any>PaginationContent(
    items: LazyPagingItems<T>,
    loadingContent: @Composable () -> Unit,
    //errorContent: @Composable (UiText) -> Unit = {},
    content: @Composable (LazyPagingItems<T>/*, UiText?*/) -> Unit,
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
                content(items/*, parseErrorMessage(error)*/)
            }

            error != null -> {
                //errorContent(parseErrorMessage(error))
            }

            else -> {
                content(items/*, null*/)
            }
        }
    }
}

/*
private fun parseErrorMessage(pagingError: LoadState.Error?): UiText {
    pagingError?.let {
        return when (val error = it.error) {
            is PaginationException -> {
                error.error.toUiText()
            }
            else -> {
                error.message?.let { message ->
                    UiText.DynamicString(message)
                } ?: run {
                    UiText.StringResource(R.string.unknown_error)
                }
            }
        }
    }

    return UiText.StringResource(R.string.unknown_error)
}
*/