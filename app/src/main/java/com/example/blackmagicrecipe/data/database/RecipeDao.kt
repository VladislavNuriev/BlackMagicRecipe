package com.example.blackmagicrecipe.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.blackmagicrecipe.data.database.models.CoffeeProductDbEntity
import com.example.blackmagicrecipe.data.database.models.RecipeDbEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipes")
    fun getRecipesList() : Flow<List<RecipeDbEntity>>

    @Query("SELECT * FROM recipes WHERE id == :recipeId LIMIT 1")
    fun getRecipe(recipeId: Int) : RecipeDbEntity

    @Insert(entity = RecipeDbEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: RecipeDbEntity)

    @Insert(entity = CoffeeProductDbEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoffeeProductList(product: List<CoffeeProductDbEntity>)

    @Query("SELECT * FROM coffee_products WHERE name LIKE '%' ||:query|| '%' ")
    suspend fun getProductsBySymbols(query: String): List<CoffeeProductDbEntity>
}