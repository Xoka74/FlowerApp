package com.shurdev.auth.data.api

import com.shurdev.auth.data.models.AuthData
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {
    // TODO: Добавить префикс /api к запросу и избавиться от хардкода адреса сервера
    @GET("http://51.250.39.65/auth/google/{authCode}")
    suspend fun postLogin(@Path("authCode") authCode: String): AuthData
}