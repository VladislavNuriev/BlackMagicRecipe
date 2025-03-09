package com.example.blackmagicrecipe.di

import android.app.Application
import com.example.blackmagicrecipe.presentation.brewingFragment.BrewingFragment
import com.example.blackmagicrecipe.presentation.recipesListFragment.RecipesListFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        DataModule::class,
        ViewModelModule::class
    ]
)
interface ApplicationComponent {

    fun inject(fragment: RecipesListFragment)

    fun inject(fragment: BrewingFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): ApplicationComponent
    }

}