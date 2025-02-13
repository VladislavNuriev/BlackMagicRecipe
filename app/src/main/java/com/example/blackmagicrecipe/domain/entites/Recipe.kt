package com.example.blackmagicrecipe.domain.entites

data class Recipe(
    val brewingType: BrewingType,
    val coffeeProduct: CoffeeProduct,
    val brewingTime: Int?,
    val evaluation: CoffeeEvaluation,
    var recipeId: Int = UNDEFINED_ID
) {

    companion object {

        const val UNDEFINED_ID = 0
    }

}