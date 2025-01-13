package com.shurdev.watering

import android.app.NotificationManager
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class WateringModule {

    @Provides
    @Singleton
    fun provideNotificationManager(@ApplicationContext context: Context): NotificationManager {
        return context.getSystemService(NotificationManager::class.java)
    }

    @Provides
    fun provideWateringNotificationSender(
        @ApplicationContext context: Context,
        notificationManager: NotificationManager,
    ): WateringNotificationLauncher {
        return WateringNotificationLauncher(
            context = context,
            notificationManager = notificationManager,
        )
    }
}