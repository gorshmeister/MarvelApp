package com.example.marvel.di

import com.example.marvel.BuildConfig
import com.example.marvel.data.network.MarvelService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import kotlinx.serialization.json.Json
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val timeout = 10L

@Module
class NetworkModule {

    private val HEADER = "apikey"
    private val TS_HEADER = "ts"
    private val TS = "1"
    private val HASH_HEADER = "hash"
    private val HASH = "06af95e52ab62c0ec2755cf9df6a5ef6"
    private val API_KEY = "b583217e5cde77d000426d3fa79360e1"

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
    }

    @Provides
    @Singleton
    fun provideConverterFactory(): Converter.Factory {
        return Json {
            ignoreUnknownKeys = true
            isLenient = true
        }.asConverterFactory("application/json".toMediaType())
    }

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        return Interceptor { chain ->
            val url = chain.request().url
                .newBuilder()
                .addQueryParameter(HEADER, API_KEY)
                .addQueryParameter(TS_HEADER, TS)
                .addQueryParameter(HASH_HEADER, HASH)
                .build()

            val request = chain.request().newBuilder().url(url).build()

            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    fun provideHttpClient(
        interceptor: Interceptor,
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addNetworkInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideCardService(
        httpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): MarvelService {
        val retrofit = Retrofit.Builder()
            .client(httpClient)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(converterFactory)
            .build()
        return retrofit.create(MarvelService::class.java)
    }
}
