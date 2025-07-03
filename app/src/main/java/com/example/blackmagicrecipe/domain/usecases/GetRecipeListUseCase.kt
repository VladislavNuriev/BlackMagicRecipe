package com.example.blackmagicrecipe.domain.usecases

import com.example.blackmagicrecipe.domain.models.Recipe
import com.example.blackmagicrecipe.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecipeListUseCase @Inject constructor(private val repository: RecipeRepository) {
    operator fun invoke(): Flow<List<Recipe>> = repository.getRecipesList()
}