package com.example.blackmagicrecipe.domain.usecases

import com.example.blackmagicrecipe.domain.entites.Recipe
import com.example.blackmagicrecipe.domain.repository.RecipesRepository

class SaveRecipeUseCase (private val repository: RecipesRepository) {
    suspend operator fun invoke(recipe: Recipe) = repository.saveRecipe(recipe)
}