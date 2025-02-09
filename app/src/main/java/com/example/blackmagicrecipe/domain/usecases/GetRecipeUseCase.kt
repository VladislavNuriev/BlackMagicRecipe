package com.example.blackmagicrecipe.domain.usecases

import com.example.blackmagicrecipe.domain.entites.Recipe
import com.example.blackmagicrecipe.domain.repository.RecipeRepository

class GetRecipeUseCase(private val repository: RecipeRepository) {
    suspend fun getRecipe(recipeId: Int): Recipe {
        return repository.getRecipe(recipeId)
    }
}