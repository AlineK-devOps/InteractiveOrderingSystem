package ru.nstu.ordsys.order.data.model

import com.google.gson.annotations.SerializedName

data class OrderItemModel(
    @SerializedName("dishId")
    val dishId: Int,
    @SerializedName("count")
    val count: Int
)