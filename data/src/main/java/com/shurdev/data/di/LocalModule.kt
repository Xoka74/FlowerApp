package com.shurdev.data.di

import android.content.Context
import android.content.SharedPreferences
import com.shurdev.data.const.LocalKeys
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(LocalKeys.FILENAME, Context.MODE_PRIVATE)
    }
}