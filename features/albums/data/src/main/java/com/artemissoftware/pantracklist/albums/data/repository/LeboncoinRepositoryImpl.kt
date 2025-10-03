package com.artemissoftware.pantracklist.albums.data.repository

import android.database.sqlite.SQLiteException
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.artemissoftware.pantracklist.album.domain.models.Album
import com.artemissoftware.pantracklist.album.domain.repository.LeboncoinRepository
import com.artemissoftware.pantracklist.albums.data.mapper.toAlbum
import com.artemissoftware.pantracklist.albums.data.mapper.toEntity
import com.artemissoftware.pantracklist.albums.data.network.source.LeboncoinApiSource
import com.artemissoftware.pantracklist.database.dao.AlbumDao
import com.artemissoftware.pantracklist.database.entities.AlbumEntity
import com.artemissoftware.pantracklist.domain.Resource
import com.artemissoftware.pantracklist.domain.error.DataError
import com.artemissoftware.pantracklist.domain.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LeboncoinRepositoryImpl(
    private val leboncoinApiSource: LeboncoinApiSource,
    private val albumDao: AlbumDao
): LeboncoinRepository {
    override suspend fun downloadAlbums(forceReload: Boolean): Resource<Unit> {

        if(albumDao.getCount() > 0 && !forceReload)
            return Resource.Success(Unit)

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
        return leboncoinApiSource.getAlbums().map { list -> list.map { it.toEntity() } }
    }

    override fun getAlbums(): Flow<PagingData<Album>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 20
            ),
            pagingSourceFactory = { albumDao.getAlbums() },
        ).flow
            .map { value: PagingData<AlbumEntity> ->
                value.map {
                    it.toAlbum() }
            }
    }
}