package com.artemissoftware.pantracklist.data.repository

import android.database.sqlite.SQLiteException
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.artemissoftware.pantracklist.core.domain.HandleRequest
import com.artemissoftware.pantracklist.core.domain.Resource
import com.artemissoftware.pantracklist.core.domain.error.DataError
import com.artemissoftware.pantracklist.data.database.dao.AlbumDao
import com.artemissoftware.pantracklist.data.database.entities.AlbumEntity
import com.artemissoftware.pantracklist.data.mapper.toAlbum
import com.artemissoftware.pantracklist.data.mapper.toEntity
import com.artemissoftware.pantracklist.data.network.source.LeboncoinApiSource
import com.artemissoftware.pantracklist.domain.models.Album
import com.artemissoftware.pantracklist.domain.repository.LeboncoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LeboncoinRepositoryImpl(
    private val leboncoinApiSource: LeboncoinApiSource,
    private val albumDao: AlbumDao
): LeboncoinRepository {
    override suspend fun downloadAlbums(): Resource<Unit> {

        val result = getAlbumCatalog()

        return when(result){
            is Resource.Failure -> Resource.Failure(result.error)
            is Resource.Success -> {
                try {
                    albumDao.reload(albums = result.data)
                    Resource.Success(Unit)
                } catch(e: SQLiteException) {
                    Resource.Failure(DataError.Local.DISK_FULL)
                }
            }
        }
    }

    private suspend fun getAlbumCatalog(): Resource<List<AlbumEntity>> {
        return HandleRequest.safeRequest {
            leboncoinApiSource.getAlbums().map { it.toEntity() }
        }
    }

    override fun getAlbums(): Flow<PagingData<Album>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 20,
            ),
            pagingSourceFactory = { albumDao.pagingSource() },
        ).flow
            .map { value: PagingData<AlbumEntity> ->
                value.map { it.toAlbum() }
            }
    }
}