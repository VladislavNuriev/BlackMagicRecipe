package com.example.blackmagicrecipe.data.database.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.blackmagicrecipe.domain.models.BrewingType

@Entity(tableName = "recipes")
data class RecipeDbEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val brewingType: BrewingType,
    @Embedded
    val coffeeProduct: CoffeeProductDbEntity,
    val brewingTime: Int,
    @Embedded
    val evaluation: CoffeeEvaluationDbModel
)