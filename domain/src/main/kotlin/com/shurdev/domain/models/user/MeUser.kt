package com.shurdev.domain.models.user

data class MeUser(
    val googleId: String,
    val name: String,
    val surname: String,
    val email: String?,
    val telegram: String?,
)