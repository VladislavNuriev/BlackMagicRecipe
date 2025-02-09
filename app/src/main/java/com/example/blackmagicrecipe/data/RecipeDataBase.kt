package com.example.blackmagicrecipe.data

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.blackmagicrecipe.data.models.RecipeDbModel

@Database(entities = [RecipeDbModel::class], version = 1, exportSchema = false)
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
                    "shop_item.db"
                )
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = db
                return db
            }
        }
    }
}