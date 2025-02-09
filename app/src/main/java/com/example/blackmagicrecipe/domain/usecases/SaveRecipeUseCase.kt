package com.example.blackmagicrecipe.domain.usecases

import com.example.blackmagicrecipe.domain.entites.Recipe
import com.example.blackmagicrecipe.domain.repository.RecipeRepository

class SaveRecipeUseCase (private val repository: RecipeRepository) {
    suspend operator fun invoke(recipe: Recipe) = repository.saveRecipe(recipe)
}