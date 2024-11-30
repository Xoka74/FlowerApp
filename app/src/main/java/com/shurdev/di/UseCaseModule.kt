package com.shurdev.di

import com.shurdev.domain.repositories.MyPlantsRepository
import com.shurdev.domain.usecases.CreateMyPlantUseCase
import com.shurdev.domain.usecases.DeleteMyPlantUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideCreateMyPlantUseCase(
        myPlantsRepository: MyPlantsRepository,
    ) = CreateMyPlantUseCase(
        myPlantsRepository = myPlantsRepository,
    )

    @Provides
    @ViewModelScoped
    fun provideDeleteMyPlantUseCase(
        myPlantsRepository: MyPlantsRepository,
    ) = DeleteMyPlantUseCase(
        myPlantsRepository = myPlantsRepository,
    )
}