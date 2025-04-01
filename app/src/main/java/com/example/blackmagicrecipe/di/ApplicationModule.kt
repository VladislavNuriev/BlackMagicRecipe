package com.example.blackmagicrecipe.di

import com.example.blackmagicrecipe.data.RecipeRepositoryImpl
import com.example.blackmagicrecipe.domain.repository.RecipeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ApplicationModule {

    @Binds
    @Singleton
    fun bindRecipeRepository(repository: RecipeRepositoryImpl): RecipeRepository
}