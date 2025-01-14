package com.shurdev.auth.data.authenticator

import com.shurdev.auth.domain.repositories.RefreshAuthRepository
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class AppAuthenticator(
    private val refreshAuthRepository: RefreshAuthRepository,
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request {
        // TODO: Implement

        return response.request.newBuilder().build()
    }
}