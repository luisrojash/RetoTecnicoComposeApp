package com.lerp.networking.di

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
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(
        baseUrl: String
    ): Retrofit =
        Retrofit.Builder()
            .client(
                OkHttpClient
                    .Builder()
                    .connectTimeout(300, java.util.concurrent.TimeUnit.SECONDS)
                    .readTimeout(300, java.util.concurrent.TimeUnit.SECONDS)
                    .writeTimeout(300, java.util.concurrent.TimeUnit.SECONDS)
                    .addInterceptor(HttpLoggingInterceptor().setLevel(getLevelInterceptor()))
                    .build()
            )
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    @Provides
    @Singleton
    fun provideBaseUrl(): String = "https://27d0092aaba74a1bb9a23584d63d35b2.api.mockbin.io"

}

fun getLevelInterceptor(): HttpLoggingInterceptor.Level {
    return HttpLoggingInterceptor.Level.BODY
}