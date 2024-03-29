package com.example.movedb.di

import android.content.Context
import com.example.movedb.config.AppConfig
import com.example.movedb.data.network.ConnectivityDataSource
import com.example.movedb.data.retrofit.RetrofitManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Provides
    @Singleton
    fun provideConnectivityDataSource(
        @ApplicationContext context: Context
    ): ConnectivityDataSource {
        return ConnectivityDataSource(
            applicationContext = context
        )
    }

    @DefaultApiQualifier
    @Provides
    fun provideRetrofitManager(
        gson: Gson, connectivityDataSource: ConnectivityDataSource
    ): RetrofitManager {
        return RetrofitManager(
            gson = gson,
            connectivityDataSource = connectivityDataSource,
            baseUrl = AppConfig.backendEnvironment.baseUrl,
            jwtToken = AppConfig.backendEnvironment.jwtToken
        )
    }
}