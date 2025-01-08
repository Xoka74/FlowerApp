package com.shurdev.watering

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WateringReceiver : BroadcastReceiver() {
    @Inject
    lateinit var launcher: WateringNotificationLauncher

    override fun onReceive(context: Context, intent: Intent) {
        launcher(intent)
    }
}