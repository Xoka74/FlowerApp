package com.shurdev.data.di

import com.shurdev.data.repositories.SurveyRepositoryImpl
import com.shurdev.domain.repositories.SurveyRepository
import com.shurdev.data.repositories.PlantRepositoryImpl
import com.shurdev.domain.repositories.PlantRepository
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
    fun bindSurveyRepository(impl: SurveyRepositoryImpl): SurveyRepository
}