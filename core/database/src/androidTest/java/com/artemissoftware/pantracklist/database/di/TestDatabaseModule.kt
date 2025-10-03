package com.artemissoftware.pantracklist.database.di

import android.content.Context
import androidx.room.Room
import com.artemissoftware.pantracklist.database.PanTracklistDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DataBaseModule::class]
)
@Module
class TestDatabaseModule {

    @Singleton
    @Provides
    fun provideTestDB(@ApplicationContext context: Context): PanTracklistDatabase {
        return Room.inMemoryDatabaseBuilder(
            context,
            PanTracklistDatabase::class.java
        ).build()
    }

    @Singleton
    @Provides
    fun provideAlbumDao(database: PanTracklistDatabase) = database.getAlbumDao()
}