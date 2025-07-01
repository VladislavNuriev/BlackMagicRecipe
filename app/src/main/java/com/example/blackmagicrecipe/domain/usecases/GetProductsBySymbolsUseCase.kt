package com.example.blackmagicrecipe.domain.usecases

import androidx.lifecycle.LiveData
import com.example.blackmagicrecipe.domain.models.CoffeeProduct
import com.example.blackmagicrecipe.domain.repository.RecipeRepository
import javax.inject.Inject

class GetProductsBySymbolsUseCase @Inject constructor(private val repository: RecipeRepository) {
    suspend operator fun invoke(query: String): List<CoffeeProduct> =
        repository.getProductsBySymbols(query)
}