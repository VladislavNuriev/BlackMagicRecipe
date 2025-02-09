package com.example.blackmagicrecipe.domain.entites

data class CoffeeProduct (
    val name:String,
    val region: String?,
    val imageUrl: String?,
    val productId: Int = UNDEFINED_ID
){
    companion object {

        const val UNDEFINED_ID = 0
    }
}