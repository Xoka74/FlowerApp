package com.shurdev.data.mappers

import com.shurdev.data.models.MeUserDto
import com.shurdev.domain.models.user.MeUser

fun MeUserDto.toDomainModel(): MeUser {
    return MeUser(
        googleId = googleId,
        name = name,
        surname = surname,
        email = email,
        telegram = telegram
    )
}