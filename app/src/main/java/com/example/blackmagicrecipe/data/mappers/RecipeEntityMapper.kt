package com.example.blackmagicrecipe.data.mappers

import com.example.blackmagicrecipe.data.database.models.CoffeeEvaluationDbModel
import com.example.blackmagicrecipe.data.database.models.RecipeDbEntity
import com.example.blackmagicrecipe.domain.models.CoffeeEvaluation
import com.example.blackmagicrecipe.domain.models.Recipe
import javax.inject.Inject

class RecipeEntityMapper @Inject constructor(private val coffeeProductEntityMapper: CoffeeProductEntityMapper) {

    fun mapRecipeToDbEntity(recipe: Recipe): RecipeDbEntity =
        RecipeDbEntity(
            id = recipe.recipeId,
            brewingType = recipe.brewingType,
            coffeeProduct = coffeeProductEntityMapper.mapCoffeeProductToDbEntity(recipe.coffeeProduct),
            brewingTime = recipe.brewingTime,
            evaluation = mapCoffeeEvaluationToDbModel(recipe.evaluation)
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
            coffeeProduct = coffeeProductEntityMapper.mapCoffeeProductDbEntityToCoffeeProduct(recipeDb.coffeeProduct),
            brewingTime = recipeDb.brewingTime,
            evaluation = mapCoffeeEvaluationDbModelToCoffeeEvaluation(recipeDb.evaluation)
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