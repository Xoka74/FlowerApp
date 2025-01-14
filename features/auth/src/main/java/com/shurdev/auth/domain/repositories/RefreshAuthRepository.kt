package com.shurdev.auth.domain.repositories

interface RefreshAuthRepository {
    suspend fun updateTokens()
}