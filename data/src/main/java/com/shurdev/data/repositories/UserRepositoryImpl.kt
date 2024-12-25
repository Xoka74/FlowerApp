package com.shurdev.data.repositories

import com.shurdev.domain.models.user.User
import com.shurdev.domain.repositories.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor() : UserRepository {
    override suspend fun getUser(): User = User(
        name = "John Doe",
        imageLink = "https://avatar.iran.liara.run/public/15"
    )
}