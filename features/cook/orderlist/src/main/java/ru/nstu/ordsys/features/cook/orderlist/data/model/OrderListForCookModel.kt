package ru.nstu.ordsys.features.cook.orderlist.data.model

import com.google.gson.annotations.SerializedName

data class OrderListForCookModel(
    @SerializedName("tableId")
    val tableId: Long,
    @SerializedName("order")
    val order: List<OrderItemForCookModel>
)