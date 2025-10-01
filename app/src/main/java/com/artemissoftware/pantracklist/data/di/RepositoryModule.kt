package com.artemissoftware.pantracklist.data.di

import com.artemissoftware.pantracklist.data.database.dao.AlbumDao
import com.artemissoftware.pantracklist.data.network.source.LeboncoinApiSource
import com.artemissoftware.pantracklist.data.repository.LeboncoinRepositoryImpl
import com.artemissoftware.pantracklist.domain.repository.LeboncoinRepository
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