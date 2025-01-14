package com.shurdev.flowerapp.di.modules

import com.shurdev.auth.di.qualifiers.ServerClientId
import com.shurdev.flowerapp.BuildConfig
import com.shurdev.flowerapp.di.qualifiers.BaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ConfigModule {

    @Provides
    @Singleton
    @ServerClientId
    fun provideServerClientId(): String = BuildConfig.serverClientId

    @Provides
    @Singleton
    @BaseUrl
    fun provideBaseUrl(): String = BuildConfig.baseUrl
}