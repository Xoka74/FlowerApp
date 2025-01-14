package com.shurdev.data.models

data class MeUserDto(
    val googleId: String,
    val name: String,
    val surname: String,
    val email: String?,
    val telegram: String?,
)