package com.example.blackmagicrecipe.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.blackmagicrecipe.data.database.RecipeDao
import com.example.blackmagicrecipe.domain.models.Recipe
import com.example.blackmagicrecipe.domain.repository.RecipeRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepositoryImpl @Inject constructor(
    application: Application,
    private val recipeDao: RecipeDao,
    private val mapper: Mapper
) : RecipeRepository {

    override fun getRecipe(id: Int): Recipe {
        val recipeDbModel = recipeDao.getRecipe(id)
        return mapper.mapRecipeDbEntityToRecipe(recipeDbModel)
    }

    override fun getRecipesList(): LiveData<List<Recipe>> {
        return recipeDao.getRecipesList().map {
            mapper.mapRecipeDbEntityListToRecipeList(it)
        }
    }

    override suspend fun saveRecipe(recipe: Recipe) {
        recipeDao.insertRecipe(mapper.mapRecipeToDbEntity(recipe))
    }
}