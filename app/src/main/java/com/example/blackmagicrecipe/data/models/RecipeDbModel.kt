package com.example.blackmagicrecipe.data.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.blackmagicrecipe.domain.entites.BrewingType

@Entity(tableName = "recipes")
data class RecipeDbModel (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var brewingType: BrewingType?,
    @Embedded
    val coffeeProduct: CoffeeProductDbModel?,
    val brewingTime: Int?,
    @Embedded
    val evaluation: CoffeeEvaluationDbModel
)