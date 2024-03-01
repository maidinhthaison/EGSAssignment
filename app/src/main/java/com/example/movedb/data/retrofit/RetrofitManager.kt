package com.example.movedb.data.retrofit

import com.example.movedb.BuildConfig
import com.example.movedb.config.AppConfig
import com.example.movedb.config.BackendEnvironment
import com.example.movedb.data.network.ConnectivityDataSource
import com.example.movedb.data.retrofit.interceptors.HttpLoggingInterceptor
import com.example.movedb.domain.repository.MovieRepository
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit

class RetrofitManager (
    private val gson: Gson,
    private val connectivityDataSource: ConnectivityDataSource,
    private val baseUrl: String,
    private val jwtToken: String
) {
    class NetworkLogger : HttpLoggingInterceptor.Logger {
        override fun log(message: String?) {
            Timber.d(message)
        }
    }

    private fun createHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addNetworkInterceptor { chain ->
                // validate network connection
                if (!connectivityDataSource.connected) {
                    throw UnknownHostException()
                }

                val newRequestBuilder = chain.request().newBuilder()
                newRequestBuilder.addHeader("accept", "application/json")
                newRequestBuilder.addHeader("Authorization", "Bearer $jwtToken")
                val newRequest = newRequestBuilder.build()
                return@addNetworkInterceptor chain.proceed(newRequest)
            }
            if (BuildConfig.DEBUG) {
                if (AppConfig.backendEnvironment == BackendEnvironment.Dev || AppConfig.backendEnvironment == BackendEnvironment.Staging) {
                    addNetworkInterceptor(HttpLoggingInterceptor(NetworkLogger()).apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                }
            }
        }.connectTimeout(90, TimeUnit.SECONDS).callTimeout(90, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS).build()
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson)).client(createHttpClient())
            .build()
    }

    operator fun <T> get(apiServiceClazz: Class<T>): T {
        return createRetrofit().create(apiServiceClazz)
    }
}
