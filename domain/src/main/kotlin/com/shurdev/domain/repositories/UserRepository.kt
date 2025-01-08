package com.shurdev.domain.repositories

import com.shurdev.domain.models.user.User

interface UserRepository {
    suspend fun getUser() : User
}