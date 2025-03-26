package com.example.blackmagicrecipe.data.mappers

import com.example.blackmagicrecipe.data.database.models.CoffeeProductDbEntity
import com.example.blackmagicrecipe.data.network.CoffeeProductDto
import com.example.blackmagicrecipe.domain.models.CoffeeProduct
import javax.inject.Inject

class CoffeeProductEntityMapper @Inject constructor() {

    fun mapCoffeeProductToDbEntity(coffeeProduct: CoffeeProduct): CoffeeProductDbEntity =
        CoffeeProductDbEntity(
            productId = coffeeProduct.productId,
            name = coffeeProduct.name,
            region = coffeeProduct.region,
            recommendedBrewingType = coffeeProduct.brewingType,
            description = coffeeProduct.description,
            evaluation = coffeeProduct.evaluation,
            price = coffeeProduct.price,
            imageUrl = coffeeProduct.imageUrl
        )

    fun mapCoffeeProductDbEntityToCoffeeProduct(coffeeProductDb: CoffeeProductDbEntity):
            CoffeeProduct = CoffeeProduct(
        productId = coffeeProductDb.productId,
        name = coffeeProductDb.name,
        region = coffeeProductDb.region,
        brewingType = coffeeProductDb.recommendedBrewingType,
        description = coffeeProductDb.description,
        evaluation = coffeeProductDb.evaluation,
        price = coffeeProductDb.price,
        imageUrl = coffeeProductDb.imageUrl
    )

    private fun mapCoffeeProductDtoToDbEntity(
        coffeeProductDto: CoffeeProductDto
    ): CoffeeProductDbEntity = CoffeeProductDbEntity(
        productId = coffeeProductDto.id.toInt(),
        name = coffeeProductDto.name,
        region = coffeeProductDto.region,
        recommendedBrewingType = coffeeProductDto.brewingType,
        description = coffeeProductDto.description,
        evaluation = coffeeProductDto.evaluation,
        price = coffeeProductDto.price,
        imageUrl = coffeeProductDto.img
    )

    fun mapCoffeeProductDtoListToDbEntityList(
        dtoList: List<CoffeeProductDto>
    ): List<CoffeeProductDbEntity> = dtoList.map(::mapCoffeeProductDtoToDbEntity)
}