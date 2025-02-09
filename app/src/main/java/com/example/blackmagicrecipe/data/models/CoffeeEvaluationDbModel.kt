package com.example.blackmagicrecipe.data.models

import androidx.room.Entity

@Entity(tableName = "coffee_evaluation")
data class CoffeeEvaluationDbModel(
    val acidity: Int?,
    val body: Int?,
    val sweetness: Int?,
    val overallRating: Int?
)
