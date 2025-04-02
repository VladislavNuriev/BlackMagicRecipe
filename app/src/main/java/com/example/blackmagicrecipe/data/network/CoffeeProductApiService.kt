package com.example.blackmagicrecipe.data.network

import retrofit2.http.GET

interface CoffeeProductApiService {

    @GET("kvuk9lp2h0jrr")
    suspend fun getCoffeeProducts(): List<CoffeeProductDto>
}