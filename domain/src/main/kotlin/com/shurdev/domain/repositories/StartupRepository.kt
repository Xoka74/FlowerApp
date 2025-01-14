package com.shurdev.domain.repositories

interface StartupRepository {
    suspend fun isFirstRun() : Boolean
    suspend fun setFirstRun()
}