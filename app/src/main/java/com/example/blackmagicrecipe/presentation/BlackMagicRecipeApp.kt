package com.example.blackmagicrecipe.presentation

import android.app.Application
import com.example.blackmagicrecipe.di.DaggerApplicationComponent

class BlackMagicRecipeApp: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}