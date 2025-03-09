package com.example.blackmagicrecipe.di

import android.app.Application
import com.example.blackmagicrecipe.data.RecipeRepositoryImpl
import com.example.blackmagicrecipe.data.database.RecipeDao
import com.example.blackmagicrecipe.data.database.RecipeDataBase
import com.example.blackmagicrecipe.domain.repository.RecipeRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface DataModule {

    @Binds
    fun bindRecipeRepository(repository: RecipeRepositoryImpl): RecipeRepository

    companion object{

        @Provides
        fun provideRecipeDao(application: Application): RecipeDao {
            return RecipeDataBase.getInstance(application).shopListDao()
        }
    }
}