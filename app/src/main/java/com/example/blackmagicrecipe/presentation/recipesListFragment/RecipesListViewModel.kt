package com.example.blackmagicrecipe.presentation.recipesListFragment

import androidx.lifecycle.ViewModel
import com.example.blackmagicrecipe.domain.usecases.GetRecipeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipesListViewModel @Inject constructor(
    private val getRecipeListUseCase: GetRecipeListUseCase
) : ViewModel() {

    val recipesList = getRecipeListUseCase.invoke()
}