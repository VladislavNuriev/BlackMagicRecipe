package com.example.blackmagicrecipe.presentation.converters

import com.example.blackmagicrecipe.R
import com.example.blackmagicrecipe.domain.models.BrewingType

fun convertBrewingTypeToDrawableId(brewingType: BrewingType): Int {
    return when(brewingType) {
        BrewingType.EspressoMachine -> R.drawable.icon_brewing_type_coffee_machine_espresso
        BrewingType.V60 -> R.drawable.icon_brewing_type_v60
        BrewingType.MochaPot -> R.drawable.icon_brewing_type_moka_pot
        BrewingType.Cup -> R.drawable.icon_brewing_type_cup
        BrewingType.Aeropress -> R.drawable.icon_brewing_type_aeropress
        BrewingType.Chemex -> R.drawable.icon_brewing_type_chemex
        BrewingType.FrenchPress -> R.drawable.icon_brewing_type_french_press
    }
}