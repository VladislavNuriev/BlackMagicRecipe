package com.example.blackmagicrecipe.domain.usecases

import com.example.blackmagicrecipe.domain.repository.RecipeRepository
import javax.inject.Inject

class LoadCoffeeProductsUseCase @Inject constructor(private val repository: RecipeRepository) {
    suspend operator fun invoke(): Result<Unit> = repository.loadCoffeeProducts()
}