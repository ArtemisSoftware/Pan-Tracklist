package com.artemissoftware.pantracklist.feature.album.presentation.util

import androidx.paging.AsyncPagingDataDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import com.artemissoftware.pantracklist.album.domain.models.Album
import kotlinx.coroutines.CoroutineDispatcher

class TestAlbumDiffer(
    dispatcher: CoroutineDispatcher
) {

    val differ = AsyncPagingDataDiffer(
        diffCallback = albumDiffCallback,
        updateCallback = NoopListCallback(),
        mainDispatcher = dispatcher,
        workerDispatcher = dispatcher
    )

    companion object {
        val albumDiffCallback = object : DiffUtil.ItemCallback<Album>() {
            override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean =
                oldItem == newItem
        }
    }

    private class NoopListCallback : ListUpdateCallback {
        override fun onInserted(position: Int, count: Int) {}
        override fun onRemoved(position: Int, count: Int) {}
        override fun onMoved(fromPosition: Int, toPosition: Int) {}
        override fun onChanged(position: Int, count: Int, payload: Any?) {}
    }
}