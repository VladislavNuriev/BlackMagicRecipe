package com.example.blackmagicrecipe.domain.usecases

import com.example.blackmagicrecipe.domain.repository.RecipeRepository
import javax.inject.Inject

class GetRecipeListUseCase @Inject constructor(private val repository: RecipeRepository) {
    fun invoke() = repository.getRecipesList()
}