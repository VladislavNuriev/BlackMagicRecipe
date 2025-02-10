package com.example.blackmagicrecipe.presentation.recipesListFragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.blackmagicrecipe.data.RecipeRepositoryImpl
import com.example.blackmagicrecipe.domain.usecases.GetRecipeListUseCase

class RecipesListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = RecipeRepositoryImpl(application)

    private val getRecipeListUseCase = GetRecipeListUseCase(repository)

    val recipesList = getRecipeListUseCase.invoke()
}