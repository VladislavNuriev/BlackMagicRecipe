package com.example.blackmagicrecipe.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.blackmagicrecipe.data.database.models.RecipeDbEntity

@Database(entities = [RecipeDbEntity::class], version = 1, exportSchema = false)
abstract class RecipeDataBase : RoomDatabase() {

    abstract fun shopListDao(): RecipeDao

    companion object {
        private var INSTANCE: RecipeDataBase? = null
        private val LOCK = Any()

        fun getInstance(application: Application): RecipeDataBase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    application,
                    RecipeDataBase::class.java,
                    "recipes.db"
                )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = db
                return db
            }
        }
    }
}