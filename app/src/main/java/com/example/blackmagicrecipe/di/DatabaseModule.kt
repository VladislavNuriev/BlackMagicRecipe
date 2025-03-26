package com.example.blackmagicrecipe.di

import android.app.Application
import androidx.room.Room
import com.example.blackmagicrecipe.data.database.RecipeDao
import com.example.blackmagicrecipe.data.database.RecipeDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application): RecipeDataBase {
        return Room.databaseBuilder(
            application,
            RecipeDataBase::class.java,
            "recipes.db"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    @Singleton
    fun provideRecipeDao(recipeDatabase: RecipeDataBase): RecipeDao {
        return recipeDatabase.recipeDao()
    }
}