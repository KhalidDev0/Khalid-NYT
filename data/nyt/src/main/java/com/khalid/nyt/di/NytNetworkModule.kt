package com.khalid.nyt.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import com.khalid.network.interceptors.ApiKeyQueryInterceptor
import com.khalid.network.interceptors.AuthInterceptor
import com.khalid.network.interceptors.CommonHeadersInterceptor
import com.khalid.nyt.BuildConfig
import com.khalid.nyt.api.ArticlesService
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

//TODO move to core:network if client/retrofit is shared
@Module
@InstallIn(SingletonComponent::class)
object NytNetworkModule {
    @Provides
    @Singleton
    fun json(): Json = Json { ignoreUnknownKeys = true }

    @Provides
    @Singleton
    fun okHttp(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(CommonHeadersInterceptor())
        .addInterceptor(AuthInterceptor())
        .addInterceptor(ApiKeyQueryInterceptor(BuildConfig.NYT_API_KEY))
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        })
        .build()


    @Provides
    @Singleton
    fun retrofit(json: Json, client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://api.nytimes.com/svc/")
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .client(client)
        .build()


    @Provides
    @Singleton
    fun service(retrofit: Retrofit): ArticlesService =
        retrofit.create(ArticlesService::class.java)
}