package com.example.blackmagicrecipe.domain.usecases

import com.example.blackmagicrecipe.domain.repository.RecipesRepository

class GetRecipesList (private val repository: RecipesRepository) {
    suspend operator fun invoke() = repository.getRecipesList()
}