package com.shurdev.auth.data.repositories

import android.content.Intent

typealias AuthCode = String

class GoogleAccountConsumer(
    private val googleAuthCallbackHolder: GoogleAuthCallbackHolder,
) {
    suspend fun authorize(intent: Intent): AuthCode {
        googleAuthCallbackHolder.currentFire?.launch(intent)

        val result = googleAuthCallbackHolder.channel.receive()

        val account = result.getOrThrow()

        return account.serverAuthCode ?: throw Exception()
    }
}