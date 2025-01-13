package com.shurdev.data.repositories

import com.shurdev.data.dataSource.SettingsDataSource
import com.shurdev.domain.repositories.LocalSettingsRepository
import javax.inject.Inject

class LocalSettingsRepositoryImpl @Inject constructor(
    private val localSettingsDataSource: SettingsDataSource,
) : LocalSettingsRepository {

    override suspend fun isFirstRun(): Boolean = localSettingsDataSource.isFirstRun()

    override suspend fun setFirstRun() = localSettingsDataSource.setFirstRun()
}