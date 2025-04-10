package com.example.blackmagicrecipe.presentation.brewingFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.blackmagicrecipe.domain.models.BrewingType
import com.example.blackmagicrecipe.domain.models.CoffeeEvaluation
import com.example.blackmagicrecipe.domain.models.CoffeeProduct
import com.example.blackmagicrecipe.domain.models.Recipe
import com.example.blackmagicrecipe.domain.usecases.GetProductsBySymbolsUseCase
import com.example.blackmagicrecipe.domain.usecases.LoadCoffeeProductsUseCase
import com.example.blackmagicrecipe.domain.usecases.SaveRecipeUseCase
import com.example.blackmagicrecipe.presentation.mappers.BrewingTimeUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
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
    private val brewingTimeUiMapper: BrewingTimeUiMapper,
    private val loadCoffeeProducts: LoadCoffeeProductsUseCase,
    private val getProductsBySymbols: GetProductsBySymbolsUseCase
) : ViewModel() {

    private val _isTimerActive = MutableLiveData<Boolean>()
    val isTimerActive: LiveData<Boolean>
        get() = _isTimerActive

    private val _loadingStatus = MutableLiveData<String>()
    val loadingStatus: LiveData<String>
        get() = _loadingStatus

    private val _coffeeProductList = MutableLiveData<List<CoffeeProduct>>()
    val coffeeProductList: LiveData<List<CoffeeProduct>>
        get() = _coffeeProductList

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

    private fun loadCoffeeProductsSafely() {
        _loadingStatus.value = LOADING
        viewModelScope.launch {
            while (_loadingStatus.value != SUCCESS) {
                loadCoffeeProducts()
                    .onSuccess {
                        _loadingStatus.value = SUCCESS
                    }
                    .onFailure {
                        _loadingStatus.value = it.toString()
                        delay(5000)
                    }
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
        const val LOADING = "Loading"
        const val SUCCESS = "Success"
    }
}
