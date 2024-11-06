package com.shurdev.data.di

import com.shurdev.data.repositories.PlantRepositoryImpl
import com.shurdev.data.repositories.UserRepositoryImpl
import com.shurdev.domain.repositories.PlantRepository
import com.shurdev.domain.repositories.UserRepository
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
    fun bindPlantRepository(impl: PlantRepositoryImpl): PlantRepository

    @Binds
    @ViewModelScoped
    fun bindUserRepository(impl: UserRepositoryImpl): UserRepository
}