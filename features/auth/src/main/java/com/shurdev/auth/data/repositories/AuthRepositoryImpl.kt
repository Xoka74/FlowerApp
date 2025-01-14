package com.shurdev.auth.data.repositories

import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.shurdev.auth.data.api.AuthApi
import com.shurdev.auth.data.dataSource.AuthDataSource
import com.shurdev.domain.repositories.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val authApi: AuthApi,
    private val googleAccountConsumer: GoogleAccountConsumer,
    private val googleSignInClient: GoogleSignInClient,
) : AuthRepository {
    override val isAuthenticated = authDataSource.isAuthenticated

    override suspend fun setup() = authDataSource.setup()

    override suspend fun login() {
        val code = googleAccountConsumer.authorize(googleSignInClient.signInIntent)

        val authData = authApi.postLogin(code)

        authDataSource.save(authData)
    }
}