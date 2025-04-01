package com.example.blackmagicrecipe.di

import com.example.blackmagicrecipe.data.network.CoffeeProductApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val BASE_URL = "https://sheetdb.io/api/v1/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }


    @Provides
    @Singleton
    fun provideCoffeeProductApiService(retrofit: Retrofit): CoffeeProductApiService {
        return retrofit.create(CoffeeProductApiService::class.java)
    }
}