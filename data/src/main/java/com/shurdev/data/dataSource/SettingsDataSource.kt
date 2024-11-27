package com.shurdev.data.dataSource

import android.content.SharedPreferences
import com.shurdev.data.const.LocalKeys
import javax.inject.Inject

class SettingsDataSource @Inject constructor(
    private val prefs: SharedPreferences,
) {
    fun isFirstRun(): Boolean = prefs.getBoolean(LocalKeys.FIRST_RUN, true)

    fun setFirstRun() {
        return with(prefs.edit()) {
            putBoolean(LocalKeys.FIRST_RUN, false)
            apply()
        }
    }
}