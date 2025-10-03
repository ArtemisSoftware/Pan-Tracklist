package com.artemissoftware.pantracklist.albums.data.di

import com.artemissoftware.pantracklist.album.domain.repository.LeboncoinRepository
import com.artemissoftware.pantracklist.albums.data.network.source.LeboncoinApiSource
import com.artemissoftware.pantracklist.albums.data.repository.LeboncoinRepositoryImpl
import com.artemissoftware.pantracklist.database.dao.AlbumDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideLeboncoinRepository(albumDao: AlbumDao, leboncoinApiSource: LeboncoinApiSource): LeboncoinRepository {
        return LeboncoinRepositoryImpl(albumDao = albumDao, leboncoinApiSource = leboncoinApiSource)
    }
}