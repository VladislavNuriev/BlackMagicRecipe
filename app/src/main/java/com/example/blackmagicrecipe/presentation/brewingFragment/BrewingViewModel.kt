package com.example.blackmagicrecipe.presentation.brewingFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.blackmagicrecipe.data.RecipeRepositoryImpl
import com.example.blackmagicrecipe.domain.entites.Recipe
import com.example.blackmagicrecipe.domain.usecases.SaveRecipeUseCase
import kotlinx.coroutines.launch

class BrewingViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = RecipeRepositoryImpl(application)

    private val saveRecipeUseCase = SaveRecipeUseCase(repository)

    suspend fun saveRecipe(recipe: Recipe) {
        viewModelScope.launch {
            saveRecipeUseCase.invoke(recipe)
        }
    }
}
