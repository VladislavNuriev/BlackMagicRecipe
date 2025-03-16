package com.example.blackmagicrecipe.domain.models

data class Recipe(
    val brewingType: BrewingType,
    val coffeeProduct: CoffeeProduct,
    val brewingTime: Int,
    val evaluation: CoffeeEvaluation,
    val recipeId: Int = UNDEFINED_ID
) {

    companion object {

        const val UNDEFINED_ID = 0
    }

}