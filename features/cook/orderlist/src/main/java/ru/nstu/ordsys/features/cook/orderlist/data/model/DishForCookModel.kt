package ru.nstu.ordsys.features.cook.orderlist.data.model

import com.google.gson.annotations.SerializedName

data class DishForCookModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("cookingTime")
    val cookingTime: Int,
    @SerializedName("imageUrl")
    val imageUrl: String,
    @SerializedName("recipe")
    val recipe: String?
)