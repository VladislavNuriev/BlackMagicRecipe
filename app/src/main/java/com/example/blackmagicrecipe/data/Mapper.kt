package com.example.blackmagicrecipe.data

import com.example.blackmagicrecipe.data.database.models.CoffeeEvaluationDbModel
import com.example.blackmagicrecipe.data.database.models.CoffeeProductDbEntity
import com.example.blackmagicrecipe.data.database.models.RecipeDbEntity
import com.example.blackmagicrecipe.domain.models.CoffeeEvaluation
import com.example.blackmagicrecipe.domain.models.CoffeeProduct
import com.example.blackmagicrecipe.domain.models.Recipe

class Mapper {

    fun mapRecipeToDbEntity(recipe: Recipe): RecipeDbEntity =
        RecipeDbEntity(
            id = recipe.recipeId,
            brewingType = recipe.brewingType,
            coffeeProduct = mapCoffeeProductToDbEntity(recipe.coffeeProduct),
            brewingTime = recipe.brewingTime,
            evaluation = mapCoffeeEvaluationToDbModel(recipe.evaluation)
        )


    private fun mapCoffeeProductToDbEntity(coffeeProduct: CoffeeProduct): CoffeeProductDbEntity =
        CoffeeProductDbEntity(
            productId = coffeeProduct.productId,
            name = coffeeProduct.name,
            region = coffeeProduct.region,
            imageUrl = coffeeProduct.imageUrl
        )

    private fun mapCoffeeEvaluationToDbModel(coffeeEvaluation: CoffeeEvaluation):
            CoffeeEvaluationDbModel = CoffeeEvaluationDbModel(
        acidity = coffeeEvaluation.acidity,
        body = coffeeEvaluation.body,
        sweetness = coffeeEvaluation.sweetness,
        overallRating = coffeeEvaluation.overallRating
    )

    fun mapRecipeDbEntityToRecipe(recipeDb: RecipeDbEntity): Recipe =
        Recipe(
            recipeId = recipeDb.id,
            brewingType = recipeDb.brewingType,
            coffeeProduct = mapCoffeeProductDbEntityToCoffeeProduct(recipeDb.coffeeProduct),
            brewingTime = recipeDb.brewingTime,
            evaluation = mapCoffeeEvaluationDbModelToCoffeeEvaluation(recipeDb.evaluation)
        )


    private fun mapCoffeeProductDbEntityToCoffeeProduct(coffeeProductDb: CoffeeProductDbEntity):
            CoffeeProduct = CoffeeProduct(
        productId = coffeeProductDb.productId,
        name = coffeeProductDb.name,
        region = coffeeProductDb.region,
        imageUrl = coffeeProductDb.imageUrl
    )

    private fun mapCoffeeEvaluationDbModelToCoffeeEvaluation(
        coffeeEvaluationDb: CoffeeEvaluationDbModel
    ): CoffeeEvaluation = CoffeeEvaluation(
        acidity = coffeeEvaluationDb.acidity,
        body = coffeeEvaluationDb.body,
        sweetness = coffeeEvaluationDb.sweetness,
        overallRating = coffeeEvaluationDb.overallRating
    )

    fun mapRecipeDbEntityListToRecipeList(recipeDbList: List<RecipeDbEntity>): List<Recipe> =
        recipeDbList.map(::mapRecipeDbEntityToRecipe)
}