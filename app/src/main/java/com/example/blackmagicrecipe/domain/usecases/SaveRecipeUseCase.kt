package com.example.blackmagicrecipe.domain.usecases

import com.example.blackmagicrecipe.domain.models.Recipe
import com.example.blackmagicrecipe.domain.repository.RecipeRepository
import javax.inject.Inject

class SaveRecipeUseCase @Inject constructor (private val repository: RecipeRepository) {
    suspend operator fun invoke(recipe: Recipe) = repository.saveRecipe(recipe)
}