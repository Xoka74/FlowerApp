package com.shurdev.auth.data.repositories

import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.shurdev.auth.data.dataSource.AuthDataSource
import com.shurdev.domain.repositories.LogoutRepository
import javax.inject.Inject

class LogoutRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource,
    private val googleSignInClient: GoogleSignInClient,
) : LogoutRepository {
    override suspend fun logout() {
        googleSignInClient.signOut()
        authDataSource.clear()
    }
}