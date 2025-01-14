package com.shurdev.flowerapp.di.modules

import com.shurdev.auth.data.authenticator.AppAuthenticator
import com.shurdev.auth.data.interceptors.LogoutInterceptor
import com.shurdev.auth.data.interceptors.TokenInterceptor
import com.shurdev.data.remote.api.MeApi
import com.shurdev.data.remote.api.PlantApi
import com.shurdev.data.remote.api.SurveyApi
import com.shurdev.flowerapp.di.qualifiers.BaseUrl
import com.shurdev.data.remote.api.TradeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        tokenInterceptor: TokenInterceptor,
        logoutInterceptor: LogoutInterceptor,
        authenticator: AppAuthenticator,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(logoutInterceptor)
            .addInterceptor(tokenInterceptor)
            .authenticator(authenticator)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        @BaseUrl baseUrl: String,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providePlantApi(retrofit: Retrofit): PlantApi {
        return retrofit.create(PlantApi::class.java)
    }

    @Provides
    @Singleton
    fun provideSurveyApi(retrofit: Retrofit): SurveyApi {
        return retrofit.create(SurveyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTradeApi(retrofit: Retrofit): TradeApi {
        return retrofit.create(TradeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMeApi(retrofit: Retrofit): MeApi {
        return retrofit.create(MeApi::class.java)
    }
}