package com.artemissoftware.pantracklist.features.albums.data.network.di

import com.artemissoftware.pantracklist.albums.data.network.di.NetworkModule
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class],
)
class TestNetworkModule : NetworkModule() {
    override fun baseUrl(): String {
        return "http://localhost:8080/"
    }
}