package com.example.blackmagicrecipe.presentation.brewingFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blackmagicrecipe.domain.models.BrewingType
import com.example.blackmagicrecipe.domain.models.CoffeeEvaluation
import com.example.blackmagicrecipe.domain.models.CoffeeProduct
import com.example.blackmagicrecipe.domain.models.Recipe
import com.example.blackmagicrecipe.domain.usecases.LoadCoffeeProductsUseCase
import com.example.blackmagicrecipe.domain.usecases.SaveRecipeUseCase
import com.example.blackmagicrecipe.presentation.mappers.BrewingTimeUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrewingViewModel @Inject constructor(
    private val saveRecipeUseCase: SaveRecipeUseCase,
    private val brewingTimeUiMapper: BrewingTimeUiMapper,
    private val loadCoffeeProductsUseCase: LoadCoffeeProductsUseCase
) : ViewModel() {

    private val _isTimerActive = MutableLiveData<Boolean>()
    val isTimerActive: LiveData<Boolean>
        get() = _isTimerActive

    init {
        viewModelScope.launch {
            loadCoffeeProductsUseCase()
        }
    }

    fun toggleTimer() {
        _isTimerActive.value = _isTimerActive.value != true
    }

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
        val coffeeProduct = CoffeeProduct(
            coffeeNameStr, "Kenya", "V60",
            "Sweet", "88", "100", "hhttpp"
        )
        val brewingTime = brewingTimeUiMapper.convertBrewingTimeStringToInt(brewingTimeStr)
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
