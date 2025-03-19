package com.example.blackmagicrecipe.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class CoffeeProductDto(

    @SerializedName("id")
    @Expose
    var id: String,

    @SerializedName("name")
    @Expose
    var name: String,

    @SerializedName("region")
    @Expose
    var region: String,

    @SerializedName("brewingtype")
    @Expose
    var brewingType: String,

    @SerializedName("description")
    @Expose
    var description: String,

    @SerializedName("evaluation")
    @Expose
    var evaluation: String,

    @SerializedName("price")
    @Expose
    var price: String,

    @SerializedName("img")
    @Expose
    var img: String
)