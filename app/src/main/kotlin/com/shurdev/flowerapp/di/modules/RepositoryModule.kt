package com.shurdev.flowerapp.di.modules

import com.shurdev.data.repositories.MyPlantsRepositoryImpl
import com.shurdev.data.repositories.PlantRepositoryImpl
import com.shurdev.data.repositories.SettingsRepositoryImpl
import com.shurdev.data.repositories.StartupRepositoryImpl
import com.shurdev.data.repositories.SurveyRepositoryImpl
import com.shurdev.data.repositories.TradeRepositoryImpl
import com.shurdev.data.repositories.UserRepositoryImpl
import com.shurdev.domain.repositories.MyPlantsRepository
import com.shurdev.domain.repositories.PlantRepository
import com.shurdev.domain.repositories.SettingsRepository
import com.shurdev.domain.repositories.StartupRepository
import com.shurdev.domain.repositories.SurveyRepository
import com.shurdev.domain.repositories.TradeRepository
import com.shurdev.domain.repositories.UserRepository
import com.shurdev.domain.repositories.WateringAlertsRepository
import com.shurdev.flowerapp.watering.WateringAlertsRepositoryImpl
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
    fun bindStartupRepository(impl: StartupRepositoryImpl): StartupRepository

    @Binds
    @ViewModelScoped
    fun bindSettingsRepository(impl: SettingsRepositoryImpl): SettingsRepository

    @Binds
    @ViewModelScoped
    fun bindMyPlantsRepository(impl: MyPlantsRepositoryImpl): MyPlantsRepository

    @Binds
    @ViewModelScoped
    fun bindTradeRepository(impl: TradeRepositoryImpl): TradeRepository

    @Binds
    @ViewModelScoped
    fun bindWateringAlertsRepository(impl: WateringAlertsRepositoryImpl): WateringAlertsRepository
}