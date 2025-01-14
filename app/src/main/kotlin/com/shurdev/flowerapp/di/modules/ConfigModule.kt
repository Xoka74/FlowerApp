package com.shurdev.flowerapp.di.modules

import com.shurdev.flowerapp.di.qualifiers.BaseUrl
import com.shurdev.flowerapp.BuildConfig
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
    @BaseUrl
    fun provideBaseUrl() : String = BuildConfig.baseUrl
}