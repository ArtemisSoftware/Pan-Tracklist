package com.artemissoftware.pantracklist.domain.repository

import android.database.sqlite.SQLiteException
import com.artemissoftware.pantracklist.core.domain.HandleRequest
import com.artemissoftware.pantracklist.core.domain.Resource
import com.artemissoftware.pantracklist.core.domain.error.DataError
import com.artemissoftware.pantracklist.data.database.dao.AlbumDao
import com.artemissoftware.pantracklist.data.database.entities.AlbumEntity
import com.artemissoftware.pantracklist.data.mapper.toEntity
import com.artemissoftware.pantracklist.data.network.source.LeboncoinApiSource
import com.artemissoftware.pantracklist.domain.models.Album

class LeboncoinRepositoryImpl(
    private val leboncoinApiSource: LeboncoinApiSource,
    private val albumDao: AlbumDao
): LeboncoinRepository {
    override suspend fun downloadAlbums(): Resource<Unit> {

        val result = getAlbums()

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

    private suspend fun getAlbums(): Resource<List<AlbumEntity>> {
        return HandleRequest.safeRequest {
            leboncoinApiSource.getAlbums().map { it.toEntity() }
        }
    }
}