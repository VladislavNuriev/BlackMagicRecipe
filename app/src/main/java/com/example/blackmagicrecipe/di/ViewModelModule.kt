package com.example.blackmagicrecipe.di

import androidx.lifecycle.ViewModel
import com.example.blackmagicrecipe.presentation.brewingFragment.BrewingViewModel
import com.example.blackmagicrecipe.presentation.recipesListFragment.RecipesListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(BrewingViewModel::class)
    fun bindBrewingViewModel(viewModel: BrewingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RecipesListViewModel::class)
    fun recipesListViewModel(viewModel: RecipesListViewModel): ViewModel
}