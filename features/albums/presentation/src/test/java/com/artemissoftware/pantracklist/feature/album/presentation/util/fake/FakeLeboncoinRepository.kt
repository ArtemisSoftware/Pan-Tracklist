package com.artemissoftware.pantracklist.feature.album.presentation.util.fake

import androidx.paging.PagingData
import com.artemissoftware.pantracklist.album.domain.models.Album
import com.artemissoftware.pantracklist.album.domain.repository.LeboncoinRepository
import com.artemissoftware.pantracklist.domain.Resource
import com.artemissoftware.pantracklist.domain.error.DataError
import com.artemissoftware.pantracklist.feature.album.presentation.util.TestData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FakeLeboncoinRepository(): LeboncoinRepository {

    private var items = emptyList<Album>()
    private var _pages = MutableStateFlow<PagingData<Album>>(PagingData.empty())

    private var pages = _pages.asStateFlow()

    var errorLoadingData = false


    override suspend fun downloadAlbums(forceReload: Boolean): Resource<Unit> {
        return if(errorLoadingData){
            Resource.Failure(DataError.Remote.SERVER)
        } else {
            items = TestData.albumList
            _pages.value = PagingData.from(items)
            Resource.Success(Unit)
        }
    }

    override fun getAlbums(): Flow<PagingData<Album>> {
        return pages
    }
}