package ru.nstu.ordsys.features.cook.orderlist.data.model

import com.google.gson.annotations.SerializedName

data class OrderItemForCookModel(
    @SerializedName("dish")
    val dish: DishForCookModel,
    @SerializedName("status")
    val status: String
)