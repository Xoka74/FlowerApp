package com.shurdev.di

import com.shurdev.data.repositories.LocalSettingsRepositoryImpl
import com.shurdev.data.repositories.MyPlantsRepositoryImpl
import com.shurdev.data.repositories.PlantRepositoryImpl
import com.shurdev.data.repositories.SurveyRepositoryImpl
import com.shurdev.data.repositories.TradeRepositoryImpl
import com.shurdev.data.repositories.UserRepositoryImpl
import com.shurdev.watering.WateringAlertsRepositoryImpl
import com.shurdev.domain.repositories.LocalSettingsRepository
import com.shurdev.domain.repositories.MyPlantsRepository
import com.shurdev.domain.repositories.PlantRepository
import com.shurdev.domain.repositories.SurveyRepository
import com.shurdev.domain.repositories.TradeRepository
import com.shurdev.domain.repositories.UserRepository
import com.shurdev.domain.repositories.WateringAlertsRepository
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

    @Binds
    @ViewModelScoped
    fun bindUserRepository(impl: UserRepositoryImpl): UserRepository

    @Binds
    @ViewModelScoped
    fun bindLocalSettingsRepository(impl: LocalSettingsRepositoryImpl): LocalSettingsRepository

    @Binds
    @ViewModelScoped
    fun bindMyPlantsRepository(impl: MyPlantsRepositoryImpl): MyPlantsRepository

    @Binds
    @ViewModelScoped
    fun bindTradeRepository(impl: TradeRepositoryImpl): TradeRepository
  
    @Binds
    @ViewModelScoped
    fun bindWateringAlertsRepository(impl: WateringAlertsRepositoryImpl) : WateringAlertsRepository
}