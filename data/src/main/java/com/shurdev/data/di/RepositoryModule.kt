package com.shurdev.data.di

import com.shurdev.data.repositories.FlowerRepositoryImpl
import com.shurdev.domain.repositories.FlowerRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    @ViewModelScoped
    fun bindFlowerRepository(impl: FlowerRepositoryImpl): FlowerRepository
}