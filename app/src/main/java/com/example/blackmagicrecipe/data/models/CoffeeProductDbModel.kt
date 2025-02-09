package com.example.blackmagicrecipe.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "coffee_products")
data class CoffeeProductDbModel (
    @PrimaryKey(autoGenerate = true)
    val productId: Int,
    val name: String,
    val region: String,
    val imageUrl: String?
)

