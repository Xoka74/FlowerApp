package com.shurdev.data.repositories

import com.shurdev.data.dataSource.StartupDataSource
import com.shurdev.domain.repositories.StartupRepository
import javax.inject.Inject

class StartupRepositoryImpl @Inject constructor(
    private val localStartupDataSource: StartupDataSource,
) : StartupRepository {

    override suspend fun isFirstRun(): Boolean = localStartupDataSource.isFirstRun()

    override suspend fun setFirstRun() = localStartupDataSource.setFirstRun()
}