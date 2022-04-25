package ru.nstu.ordsys.order.data.model

import com.google.gson.annotations.SerializedName

data class OrderListModel(
    @SerializedName("tableId")
    val tableId: Long,
    @SerializedName("order")
    val order: List<OrderItemModel>
)