package com.example.blackmagicrecipe.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.blackmagicrecipe.data.database.models.CoffeeProductDbEntity
import com.example.blackmagicrecipe.data.database.models.RecipeDbEntity

@Database(
    entities = [RecipeDbEntity::class, CoffeeProductDbEntity::class],
    version = 4,
    exportSchema = false
)
abstract class RecipeDataBase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao
}