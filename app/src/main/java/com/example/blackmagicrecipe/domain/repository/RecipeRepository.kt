package com.example.blackmagicrecipe.domain.repository

import androidx.lifecycle.LiveData
import com.example.blackmagicrecipe.domain.models.CoffeeProduct
import com.example.blackmagicrecipe.domain.models.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    fun getRecipe(id: Int): Recipe
    fun getRecipesList(): Flow<List<Recipe>>
    suspend fun saveRecipe(recipe: Recipe)
    suspend fun loadCoffeeProducts(): Result<Unit>
    suspend fun getProductsBySymbols(query: String): List<CoffeeProduct>
}