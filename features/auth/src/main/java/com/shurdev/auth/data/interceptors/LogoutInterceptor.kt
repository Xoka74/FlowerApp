package com.shurdev.auth.data.interceptors

import com.shurdev.domain.repositories.LogoutRepository
import okhttp3.Interceptor
import okhttp3.Response

class LogoutInterceptor(
    private val logoutRepository: LogoutRepository,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // TODO: Implement
        return chain.proceed(chain.request())
    }
}