package com.example.blackmagicrecipe.data

import com.example.blackmagicrecipe.data.database.RecipeDao
import com.example.blackmagicrecipe.data.mappers.CoffeeProductEntityMapper
import com.example.blackmagicrecipe.data.mappers.RecipeEntityMapper
import com.example.blackmagicrecipe.data.network.CoffeeProductApiService
import com.example.blackmagicrecipe.domain.models.CoffeeProduct
import com.example.blackmagicrecipe.domain.models.Recipe
import com.example.blackmagicrecipe.domain.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepositoryImpl @Inject constructor(
    private val recipeDao: RecipeDao,
    private val recipeEntityMapper: RecipeEntityMapper,
    private val coffeeProductEntityMapper: CoffeeProductEntityMapper,
    private val coffeeProductApiService: CoffeeProductApiService
) : RecipeRepository {

    override fun getRecipe(id: Int): Recipe {
        val recipeDbModel = recipeDao.getRecipe(id)
        return recipeEntityMapper.mapRecipeDbEntityToRecipe(recipeDbModel)
    }

    override fun getRecipesList(): Flow<List<Recipe>> {
        return recipeDao.getRecipesList().map {
            recipeEntityMapper.mapRecipeDbEntityListToRecipeList(it)
        }
    }

    override suspend fun saveRecipe(recipe: Recipe) {
        recipeDao.insertRecipe(recipeEntityMapper.mapRecipeToDbEntity(recipe))
    }

    override suspend fun loadCoffeeProducts(): Result<Unit> {
        return runCatching {
            val coffeeProductDbEntityList =
                withContext(context = Dispatchers.IO) {
                    coffeeProductEntityMapper.mapCoffeeProductDtoListToDbEntityList(
                        coffeeProductApiService.getCoffeeProducts()
                    )
                }
            recipeDao.insertCoffeeProductList(coffeeProductDbEntityList)
        }
    }

    override suspend fun getProductsBySymbols(query: String): List<CoffeeProduct> =
        withContext(context = Dispatchers.IO) {
            return@withContext recipeDao.getProductsBySymbols(query).map {
                coffeeProductEntityMapper.mapCoffeeProductDbEntityToCoffeeProduct(it)
            }
        }
}