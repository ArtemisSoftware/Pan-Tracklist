package com.artemissoftware.pantracklist.util.fake

import androidx.paging.PagingData
import com.artemissoftware.pantracklist.core.domain.Resource
import com.artemissoftware.pantracklist.core.domain.error.DataError
import com.artemissoftware.pantracklist.domain.models.Album
import com.artemissoftware.pantracklist.domain.repository.LeboncoinRepository
import com.artemissoftware.pantracklist.util.TestData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeLeboncoinRepository(): LeboncoinRepository {

    private var items = emptyList<Album>()
    private var pages = flowOf(PagingData.from(emptyList<Album>()))

    var errorLoadingData = false

    override suspend fun downloadAlbums(): Resource<Unit> {
        return if(errorLoadingData){
            Resource.Failure(DataError.Remote.SERVER)
        } else {
            items = TestData.albumList
            //pages = flowOf(PagingData.from(items))
            Resource.Success(Unit)
        }
    }

    override fun getAlbums(): Flow<PagingData<Album>> {
        return pages
    }
}