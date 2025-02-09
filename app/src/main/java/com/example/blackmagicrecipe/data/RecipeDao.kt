package com.example.blackmagicrecipe.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.blackmagicrecipe.data.models.RecipeDbModel

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipes")
    fun getRecipesList() : LiveData<List<RecipeDbModel>>

    @Query("SELECT * FROM recipes WHERE id == :recipeId LIMIT 1")
    fun getRecipe(recipeId: Int) : RecipeDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: RecipeDbModel)
}