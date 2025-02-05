package com.example.blackmagicrecipe.domain.entites

data class Recipe(
    val id: Int,
    val brewingType: BrewingType,
    val coffeeProduct: CoffeeProduct,
    val time: Int?,
    val evaluation: CoffeeEvaluation
    )