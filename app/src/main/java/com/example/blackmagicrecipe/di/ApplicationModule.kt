package com.example.blackmagicrecipe.di

import com.example.blackmagicrecipe.data.RecipeRepositoryImpl
import com.example.blackmagicrecipe.data.network.CoffeeProductApiFactory
import com.example.blackmagicrecipe.data.network.CoffeeProductApiService
import com.example.blackmagicrecipe.domain.repository.RecipeRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ApplicationModule {

    @Binds
    @Singleton
    fun bindRecipeRepository(repository: RecipeRepositoryImpl): RecipeRepository

    companion object {

        @Provides
        @Singleton
        fun provideCoffeeProductApiService() : CoffeeProductApiService {
            return CoffeeProductApiFactory.apiService
        }
    }
}