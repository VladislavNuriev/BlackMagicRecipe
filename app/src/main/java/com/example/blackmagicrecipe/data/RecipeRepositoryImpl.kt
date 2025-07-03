package com.example.blackmagicrecipe.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.blackmagicrecipe.data.database.RecipeDao
import com.example.blackmagicrecipe.data.mappers.CoffeeProductEntityMapper
import com.example.blackmagicrecipe.data.mappers.RecipeEntityMapper
import com.example.blackmagicrecipe.data.network.CoffeeProductApiService
import com.example.blackmagicrecipe.domain.models.CoffeeProduct
import com.example.blackmagicrecipe.domain.models.Recipe
import com.example.blackmagicrecipe.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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
            val coffeeProductDtoList = coffeeProductApiService.getCoffeeProducts()
            val coffeeProductDbEntityList =
                coffeeProductEntityMapper.mapCoffeeProductDtoListToDbEntityList(coffeeProductDtoList)
            recipeDao.insertCoffeeProductList(coffeeProductDbEntityList)
        }
    }

    override suspend fun getProductsBySymbols(query: String): List<CoffeeProduct> {
        return recipeDao.getProductsBySymbols(query).map {
            coffeeProductEntityMapper.mapCoffeeProductDbEntityToCoffeeProduct(it)
        }
    }
}