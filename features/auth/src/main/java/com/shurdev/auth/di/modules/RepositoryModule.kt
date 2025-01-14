package com.shurdev.auth.di.modules

import com.shurdev.auth.data.repositories.LogoutRepositoryImpl
import com.shurdev.auth.data.repositories.RefreshAuthRepositoryImpl
import com.shurdev.auth.domain.repositories.RefreshAuthRepository
import com.shurdev.domain.repositories.LogoutRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindRefreshAuthRepository(impl: RefreshAuthRepositoryImpl): RefreshAuthRepository

    @Binds
    @Singleton
    fun bindLogoutRepository(impl: LogoutRepositoryImpl): LogoutRepository
}