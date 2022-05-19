package ru.nstu.ordsys.features.waiter.orderlist.data.model

import com.google.gson.annotations.SerializedName

data class DishForWaiterModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("cookingTime")
    val cookingTime: Int
)