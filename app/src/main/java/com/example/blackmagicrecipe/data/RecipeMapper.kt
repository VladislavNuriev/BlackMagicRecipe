package com.example.blackmagicrecipe.data

import com.example.blackmagicrecipe.data.models.CoffeeEvaluationDbModel
import com.example.blackmagicrecipe.data.models.CoffeeProductDbModel
import com.example.blackmagicrecipe.data.models.RecipeDbModel
import com.example.blackmagicrecipe.domain.entites.CoffeeEvaluation
import com.example.blackmagicrecipe.domain.entites.CoffeeProduct
import com.example.blackmagicrecipe.domain.entites.Recipe

class RecipeMapper {

    fun mapRecipeEntityToDbModel(recipe: Recipe): RecipeDbModel =
        RecipeDbModel(
            id = recipe.recipeId,
            brewingType = recipe.brewingType,
            coffeeProduct = mapCoffeeProductEntityToDbModel(recipe.coffeeProduct),
            brewingTime = recipe.brewingTime,
            evaluation = mapCoffeeEvaluationEntityToDbModel(recipe.evaluation)
        )


    private fun mapCoffeeProductEntityToDbModel(coffeeProduct: CoffeeProduct?): CoffeeProductDbModel =
        CoffeeProductDbModel(
            productId = coffeeProduct!!.productId,
            name = coffeeProduct.name,
            region = coffeeProduct.region,
            imageUrl = coffeeProduct.imageUrl
        )

    private fun mapCoffeeEvaluationEntityToDbModel(coffeeEvaluation: CoffeeEvaluation):
            CoffeeEvaluationDbModel = CoffeeEvaluationDbModel(
        acidity = coffeeEvaluation.acidity,
        body = coffeeEvaluation.body,
        sweetness = coffeeEvaluation.sweetness,
        overallRating = coffeeEvaluation.overallRating
    )

    fun mapRecipeDbModelToEntity(recipeDb: RecipeDbModel): Recipe =
        Recipe(
            recipeId = recipeDb.id,
            brewingType = recipeDb.brewingType,
            coffeeProduct = mapCoffeeProductDbModelToEntity(recipeDb.coffeeProduct),
            brewingTime = recipeDb.brewingTime,
            evaluation = mapCoffeeEvaluationDbModelToEntity(recipeDb.evaluation)
        )


    private fun mapCoffeeProductDbModelToEntity(coffeeProductDb: CoffeeProductDbModel?):
            CoffeeProduct = CoffeeProduct(
        productId = coffeeProductDb!!.productId,
        name = coffeeProductDb.name,
        region = coffeeProductDb.region,
        imageUrl = coffeeProductDb.imageUrl
    )

    private fun mapCoffeeEvaluationDbModelToEntity(coffeeEvaluationDb: CoffeeEvaluationDbModel):
            CoffeeEvaluation = CoffeeEvaluation(
        acidity = coffeeEvaluationDb.acidity,
        body = coffeeEvaluationDb.body,
        sweetness = coffeeEvaluationDb.sweetness,
        overallRating = coffeeEvaluationDb.overallRating
    )


    fun mapRecipesDbModelListToEntityList(recipesDbList: List<RecipeDbModel>): List<Recipe> {
        return recipesDbList.map { mapRecipeDbModelToEntity(it) }
    }


}