package com.shurdev.auth.di.modules

import android.content.Context
import android.content.SharedPreferences
import androidx.credentials.CredentialManager
import com.google.android.gms.auth.api.identity.AuthorizationClient
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.shurdev.auth.data.api.AuthApi
import com.shurdev.auth.data.authenticator.AppAuthenticator
import com.shurdev.auth.data.dataSource.AuthDataSource
import com.shurdev.auth.data.interceptors.LogoutInterceptor
import com.shurdev.auth.data.interceptors.TokenInterceptor
import com.shurdev.auth.data.repositories.GoogleAccountConsumer
import com.shurdev.auth.data.repositories.GoogleAuthCallbackHolder
import com.shurdev.auth.di.qualifiers.RefreshAuthMutex
import com.shurdev.auth.di.qualifiers.ServerClientId
import com.shurdev.auth.domain.repositories.RefreshAuthRepository
import com.shurdev.domain.repositories.LogoutRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.sync.Mutex
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AuthModule {

    @Provides
    @Singleton
    fun provideCredentialManager(@ApplicationContext context: Context): CredentialManager {
        return CredentialManager.create(context)
    }

    @Provides
    @Singleton
    fun provideGoogleAuthCallbackHolder() = GoogleAuthCallbackHolder()

    @Provides
    @Singleton
    fun provideAuthActivityHandler(holder: GoogleAuthCallbackHolder) = GoogleAccountConsumer(holder)

    @Provides
    @Singleton
    fun provideAuthorizationClient(@ApplicationContext context: Context): AuthorizationClient {
        return Identity.getAuthorizationClient(context)
    }

    @Provides
    @Singleton
    fun provideGoogleSignInClient(
        @ServerClientId serverClientId: String,
        @ApplicationContext context: Context,
    ): GoogleSignInClient {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestServerAuthCode(serverClientId)
            .requestEmail()
            .requestProfile()
            .build()

        return GoogleSignIn.getClient(context, gso)
    }

    @Provides
    @Singleton
    fun provideAppAuthenticator(refreshAuthRepository: RefreshAuthRepository): AppAuthenticator {
        return AppAuthenticator(refreshAuthRepository)
    }

    @Provides
    @Singleton
    fun provideTokenInterceptor(authDataSource: AuthDataSource): TokenInterceptor {
        return TokenInterceptor(authDataSource)
    }

    @Provides
    @Singleton
    fun provideLogoutInterceptor(logoutRepository: LogoutRepository): LogoutInterceptor {
        return LogoutInterceptor(logoutRepository)
    }

    @Provides
    @Singleton
    fun provideAuthDataSource(prefs: SharedPreferences): AuthDataSource {
        return AuthDataSource(prefs)
    }

    @Provides
    @Singleton
    @RefreshAuthMutex
    fun provideJwtAuthRepositoryMutex(): Mutex = Mutex()

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }
}