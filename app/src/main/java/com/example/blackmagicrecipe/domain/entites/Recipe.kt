package com.example.blackmagicrecipe.domain.entites

data class Recipe(
    var recipeId: Int = UNDEFINED_ID,
    val brewingType: BrewingType,
    val coffeeProduct: CoffeeProduct,
    val brewingTime: Int?,
    val evaluation: CoffeeEvaluation
) {

    companion object {

        const val UNDEFINED_ID = 0
    }

}