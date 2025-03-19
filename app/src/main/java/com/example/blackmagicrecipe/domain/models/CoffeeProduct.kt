package com.example.blackmagicrecipe.domain.models

data class CoffeeProduct(
    val name: String,
    val region: String,
    val brewingType: String,
    val description: String,
    val evaluation: String,
    val price: String,
    val imageUrl: String,
    val productId: Int = UNDEFINED_ID
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}