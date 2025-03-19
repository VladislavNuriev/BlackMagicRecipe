package com.example.blackmagicrecipe.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object CoffeeProductApiFactory {

    private val BASE_URL = "https://sheetdb.io/api/v1/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build()

    val apiService = retrofit.create(CoffeeProductApiService::class.java)

}