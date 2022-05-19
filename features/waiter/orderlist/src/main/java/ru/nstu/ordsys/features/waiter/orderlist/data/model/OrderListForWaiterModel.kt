package ru.nstu.ordsys.features.waiter.orderlist.data.model

import com.google.gson.annotations.SerializedName

data class OrderListForWaiterModel(
    @SerializedName("tableId")
    val tableId: Long,
    @SerializedName("order")
    val order: List<OrderItemForWaiterModel>,
    @SerializedName("waiterCalled")
    val waiterCalled: Boolean
)