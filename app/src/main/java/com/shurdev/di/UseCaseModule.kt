package com.shurdev.di

import com.shurdev.domain.repositories.MyPlantsRepository
import com.shurdev.domain.repositories.WateringAlertsRepository
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
        wateringAlertsRepository: WateringAlertsRepository,
    ) = CreateMyPlantUseCase(
        myPlantsRepository = myPlantsRepository,
        wateringAlertsRepository = wateringAlertsRepository,
    )

    @Provides
    @ViewModelScoped
    fun provideDeleteMyPlantUseCase(
        myPlantsRepository: MyPlantsRepository,
        wateringAlertsRepository: WateringAlertsRepository,
    ) = DeleteMyPlantUseCase(
        myPlantsRepository = myPlantsRepository,
        wateringAlertsRepository = wateringAlertsRepository,
    )
}