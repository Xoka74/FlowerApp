package com.shurdev.data.repositories

import com.shurdev.domain.models.user.User
import com.shurdev.domain.repositories.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor() : UserRepository {
    override suspend fun getUser(): User = User(
        name = "Test user",
        imageLink = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSlyeul2vFjjUrN_lMMNkKqYFZGs1jrty9krw&s"
    )
}