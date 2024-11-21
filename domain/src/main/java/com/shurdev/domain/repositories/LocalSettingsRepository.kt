package com.shurdev.domain.repositories

interface LocalSettingsRepository {
    suspend fun isFirstRun() : Boolean
    suspend fun setFirstRun()
}