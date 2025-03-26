package com.example.blackmagicrecipe.domain.repository

import androidx.lifecycle.LiveData
import com.example.blackmagicrecipe.domain.models.Recipe

interface RecipeRepository {
    fun getRecipe(id: Int): Recipe
    fun getRecipesList(): LiveData<List<Recipe>>
    suspend fun saveRecipe(recipe: Recipe)
    suspend fun loadCoffeeProducts(): Result<Unit>
}