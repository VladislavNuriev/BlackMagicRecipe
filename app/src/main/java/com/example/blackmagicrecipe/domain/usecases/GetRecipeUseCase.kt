package com.example.blackmagicrecipe.domain.usecases

import com.example.blackmagicrecipe.domain.repository.RecipesRepository

class GetRecipeUseCase (private val repository: RecipesRepository) {
    suspend operator fun invoke() = repository.getRecipe()
}