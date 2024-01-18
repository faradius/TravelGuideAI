package com.developerscracks.travelguideai.home.di

import com.developerscracks.travelguideai.home.data.HomeRepositoryImpl
import com.developerscracks.travelguideai.home.data.remote.ApiKeyInterceptor
import com.developerscracks.travelguideai.home.data.remote.ChatgptApi
import com.developerscracks.travelguideai.home.domain.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HomeModule {

    @Provides
    @Singleton
    fun provideApi(): ChatgptApi {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor())
            .addInterceptor(ApiKeyInterceptor())
            .readTimeout(30, TimeUnit.SECONDS) // Timeout de lectura
            .writeTimeout(30, TimeUnit.SECONDS) // Timeout de escritura
            .callTimeout(30, TimeUnit.SECONDS) // Establece un timeout de 30 segundos
            .build()

        return Retrofit.Builder()
            .baseUrl(ChatgptApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideRepository(api: ChatgptApi): HomeRepository {
        return HomeRepositoryImpl(api)
    }
}