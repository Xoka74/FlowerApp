package com.shurdev.watering

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.shurdev.data.R
import com.shurdev.data.keys.NotificationKeys
import com.shurdev.flowerapp.MainActivity


class WateringNotificationLauncher(
    private val context: Context,
    private val notificationManager: NotificationManager,
) {
    operator fun invoke(wateringIntent: Intent) {
        val plantName =
            wateringIntent.getStringExtra(NotificationKeys.PLANT_NAME_KEY) ?: return

        val plantId = wateringIntent.getIntExtra(NotificationKeys.PLANT_ID_KEY, 0)

        val intent = Intent(context, MainActivity::class.java)
            .setAction(Intent.ACTION_VIEW)

        val intentToOpenApp = PendingIntent.getActivity(
            context,
            plantId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, NotificationKeys.CHANNEL_ID)
            .setContentText(null)
            .setContentTitle(context.getString(R.string.water_your_plant, plantName))
            .setContentIntent(intentToOpenApp)
            // TODO: Change to app icon
            .setSmallIcon(R.drawable.icon_water_drop_filled)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(plantId, notification)
    }
}