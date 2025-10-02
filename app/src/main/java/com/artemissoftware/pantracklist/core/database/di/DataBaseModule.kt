package com.artemissoftware.pantracklist.core.database.di

import android.content.Context
import androidx.room.Room
import com.artemissoftware.pantracklist.core.database.PanTracklistDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun providePanTracklistDatabase(
        @ApplicationContext context: Context,
    ): PanTracklistDatabase {
        return Room
            .databaseBuilder(
                context,
                PanTracklistDatabase::class.java,
                PanTracklistDatabase.DATABASE_NAME,
            )
            .build()
    }

    @Singleton
    @Provides
    fun provideAlbumDao(database: PanTracklistDatabase) = database.getAlbumDao()
}