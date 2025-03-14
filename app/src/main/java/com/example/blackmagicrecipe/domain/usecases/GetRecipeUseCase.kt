package com.example.blackmagicrecipe.domain.usecases

import com.example.blackmagicrecipe.domain.models.Recipe
import com.example.blackmagicrecipe.domain.repository.RecipeRepository
import javax.inject.Inject

class GetRecipeUseCase @Inject constructor (private val repository: RecipeRepository) {
    operator fun invoke(recipeId: Int): Recipe {
        return repository.getRecipe(recipeId)
    }
}