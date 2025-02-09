package com.example.blackmagicrecipe.domain.usecases

import com.example.blackmagicrecipe.domain.repository.RecipeRepository

class GetRecipeListUseCase (private val repository: RecipeRepository) {
    suspend operator fun invoke() = repository.getRecipesList()
}