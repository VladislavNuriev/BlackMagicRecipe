package com.example.blackmagicrecipe.presentation.brewingFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.blackmagicrecipe.data.RecipeRepositoryImpl
import com.example.blackmagicrecipe.domain.models.BrewingType
import com.example.blackmagicrecipe.domain.models.CoffeeEvaluation
import com.example.blackmagicrecipe.domain.models.CoffeeProduct
import com.example.blackmagicrecipe.domain.models.Recipe
import com.example.blackmagicrecipe.domain.usecases.SaveRecipeUseCase
import kotlinx.coroutines.launch

class BrewingViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = RecipeRepositoryImpl(application)

    private val saveRecipeUseCase = SaveRecipeUseCase(repository)

    fun saveRecipe(
        brewingTypeString: String,
        coffeeNameString: String,
        acidity: Int,
        body: Int,
        sweetness: Int,
        rating: Int
    ) {
        val brewingType = BrewingType.valueOf(
            brewingTypeString.filterNot { it.isWhitespace() }
        )
        val coffeeProduct = CoffeeProduct(coffeeNameString, "Kenya", "http")
        val time = 25
        val evaluation = CoffeeEvaluation(acidity, body, sweetness, rating)
        val newRecipe = Recipe(
            brewingType,
            coffeeProduct,
            time,
            evaluation
        )
        viewModelScope.launch {
            saveRecipeUseCase.invoke(newRecipe)
        }
    }
}
