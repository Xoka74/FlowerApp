package com.shurdev.data.dataSource

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.shurdev.data.keys.LocalKeys
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class StartupDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) {
    suspend fun isFirstRun(): Boolean {
        val key = booleanPreferencesKey(LocalKeys.FIRST_RUN)

        val firstPrefs = dataStore.data.firstOrNull()

        return firstPrefs?.get(key) ?: true
    }

    suspend fun setFirstRun() {
        val key = booleanPreferencesKey(LocalKeys.FIRST_RUN)

        dataStore.edit { settings ->
            settings[key] = false
        }
    }
}