package com.shurdev.watering

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import com.shurdev.data.keys.NotificationKeys
import com.shurdev.domain.models.myPlant.PlantWatering
import com.shurdev.domain.models.plant.PlantId
import com.shurdev.domain.repositories.WateringAlertsRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import java.time.ZoneId
import javax.inject.Inject

class WateringAlertsRepositoryImpl @Inject constructor(
    private val alarmManager: AlarmManager,
    @ApplicationContext private val context: Context,
) : WateringAlertsRepository {
    override fun schedule(
        plantName: String,
        plantId: PlantId,
        watering: PlantWatering,
    ) {
        val intent = Intent(context.applicationContext, WateringReceiver::class.java).apply {
            putExtra(NotificationKeys.PLANT_ID_KEY, plantId)
            putExtra(NotificationKeys.PLANT_NAME_KEY, plantName)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context.applicationContext,
            plantId,
            intent,
            PendingIntent.FLAG_MUTABLE,
        )

        val now =
            watering.lastWateringTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()

        return alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            now,
            watering.frequency.toMillis(),
            pendingIntent,
        )
    }

    override fun cancel(
        plantName: String,
        plantId: PlantId,
    ) {
        val intent = Intent(context.applicationContext, WateringReceiver::class.java).apply {
            putExtra(NotificationKeys.PLANT_ID_KEY, plantId)
            putExtra(NotificationKeys.PLANT_NAME_KEY, plantName)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context.applicationContext,
            plantId,
            intent,
            PendingIntent.FLAG_MUTABLE,
        )

        return alarmManager.cancel(pendingIntent)
    }

    override fun cancelAll() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            alarmManager.cancelAll()
        }
    }
}