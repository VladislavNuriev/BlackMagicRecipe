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
import com.example.blackmagicrecipe.presentation.converters.convertBrewingTimeStringToInt
import kotlinx.coroutines.launch

class BrewingViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = RecipeRepositoryImpl(application)

    private val saveRecipeUseCase = SaveRecipeUseCase(repository)

    fun saveRecipe(
        brewingTypeStr: String,
        coffeeNameStr: String,
        brewingTimeStr: String,
        acidity: Int,
        body: Int,
        sweetness: Int,
        rating: Int
    ) {
        val brewingType = BrewingType.valueOf(
            brewingTypeStr.filterNot { it.isWhitespace() }
        )
        val coffeeProduct = CoffeeProduct(coffeeNameStr, "Kenya", "http")
        val brewingTime = convertBrewingTimeStringToInt(brewingTimeStr)
        val evaluation = CoffeeEvaluation(acidity, body, sweetness, rating)
        val newRecipe = Recipe(
            brewingType,
            coffeeProduct,
            brewingTime,
            evaluation
        )
        viewModelScope.launch {
            saveRecipeUseCase.invoke(newRecipe)
        }
    }
}
