package com.shurdev.data.remote.api

import com.shurdev.data.models.MeUserDto
import retrofit2.http.GET

interface MeApi {
    @GET("me/info")
    suspend fun getUser(): MeUserDto
}