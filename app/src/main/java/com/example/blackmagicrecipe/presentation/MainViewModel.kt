package com.example.blackmagicrecipe.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.blackmagicrecipe.data.RecipeRepositoryImpl
import com.example.blackmagicrecipe.domain.entites.Recipe
import com.example.blackmagicrecipe.domain.usecases.SaveRecipeUseCase
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = RecipeRepositoryImpl(application)

    private val saveRecipeUseCase = SaveRecipeUseCase(repository)




    suspend fun saveRecipe(
        recipe: Recipe
    ) {
        viewModelScope.launch {
            saveRecipeUseCase.invoke(recipe)
        }
    }
}
