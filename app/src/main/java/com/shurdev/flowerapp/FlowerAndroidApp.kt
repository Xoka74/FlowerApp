package com.shurdev.flowerapp

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.shurdev.data.keys.NotificationKeys
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class FlowerAndroidApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                NotificationKeys.CHANNEL_ID,
                NotificationKeys.CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH,
            )

            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}