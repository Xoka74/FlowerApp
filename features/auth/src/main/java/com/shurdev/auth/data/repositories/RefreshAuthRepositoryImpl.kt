package com.shurdev.auth.data.repositories

import com.shurdev.auth.data.dataSource.AuthDataSource
import com.shurdev.auth.di.qualifiers.RefreshAuthMutex
import com.shurdev.auth.domain.repositories.RefreshAuthRepository
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject

class RefreshAuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    @RefreshAuthMutex private val mutex: Mutex,
) : RefreshAuthRepository {
    override suspend fun updateTokens() {
        // TODO: Сделать, когда будет готов бэкенд
    }
}