package com.shurdev.domain.repositories

import com.shurdev.domain.models.user.MeUser

interface MeRepository {
    suspend fun getUser(): MeUser
}