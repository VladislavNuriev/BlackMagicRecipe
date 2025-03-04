package com.example.blackmagicrecipe.domain.usecases

import com.example.blackmagicrecipe.domain.repository.RecipeRepository

class GetRecipeListUseCase (private val repository: RecipeRepository) {
    fun invoke() = repository.getRecipesList()
}