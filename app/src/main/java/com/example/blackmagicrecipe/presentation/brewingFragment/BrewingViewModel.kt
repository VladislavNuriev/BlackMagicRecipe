package com.example.blackmagicrecipe.presentation.brewingFragment

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blackmagicrecipe.domain.models.BrewingType
import com.example.blackmagicrecipe.domain.models.CoffeeEvaluation
import com.example.blackmagicrecipe.domain.models.CoffeeProduct
import com.example.blackmagicrecipe.domain.models.Recipe
import com.example.blackmagicrecipe.domain.usecases.GetProductsBySymbolsUseCase
import com.example.blackmagicrecipe.domain.usecases.LoadCoffeeProductsUseCase
import com.example.blackmagicrecipe.domain.usecases.SaveRecipeUseCase
import com.example.blackmagicrecipe.presentation.utils.convertBrewingTimeToInt
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrewingViewModel @Inject constructor(
    private val saveRecipeUseCase: SaveRecipeUseCase,
    private val loadCoffeeProducts: LoadCoffeeProductsUseCase,
    private val getProductsBySymbols: GetProductsBySymbolsUseCase
) : ViewModel() {

    private val _isTimerActive = MutableStateFlow<Boolean>(false)
    val isTimerActive = _isTimerActive.asStateFlow()

    private val _loadingStatus = MutableStateFlow<String>(INITIAL)
    val loadingStatus = _loadingStatus.asStateFlow()

    private val _coffeeProductList = MutableStateFlow<List<CoffeeProduct>>(emptyList())
    val coffeeProductList = _coffeeProductList.asStateFlow()

    private val _searchFlow = MutableStateFlow("")


    init {
        loadCoffeeProductsSafely()
        setUpSearchFlow()
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
        val brewingTime = brewingTimeStr.convertBrewingTimeToInt()
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

    private fun loadCoffeeProductsSafely() {
        viewModelScope.launch {
            loadCoffeeProducts()
                .onSuccess {
                    _loadingStatus.value = SUCCESS
                }
                .onFailure {
                    _loadingStatus.value = FAILURE
                    Log.d(TAG, "loadCoffeeProductsSafely: ${it}")
                }
        }
    }

    private fun getProductList(query: String) {
        viewModelScope.launch {
            _coffeeProductList.value = getProductsBySymbols(query)
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchFlow.value = query
    }

    private fun setUpSearchFlow() {
        _searchFlow
            .debounce(500)
            .filter { it.length >= 2 }
            .distinctUntilChanged()
            .onEach { query ->
                getProductList(query)
            }
            .launchIn(viewModelScope)
    }

    companion object {
        const val SUCCESS = "Success"
        const val INITIAL = "Initial"
        const val TAG = "LOADING_VIEWMODEL"
        const val FAILURE = "Failure"
    }
}
