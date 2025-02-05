package com.example.blackmagicrecipe.domain.repository

import androidx.lifecycle.LiveData
import com.example.blackmagicrecipe.domain.entites.Recipe

interface RecipesRepository {
    fun getRecipe(): LiveData<Recipe>
    fun getRecipesList(): LiveData<List<Recipe>>
    suspend fun saveRecipe(recipe: Recipe)
}