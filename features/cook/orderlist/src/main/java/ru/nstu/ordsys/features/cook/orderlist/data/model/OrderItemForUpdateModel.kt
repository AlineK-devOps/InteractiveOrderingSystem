package ru.nstu.ordsys.features.cook.orderlist.data.model

import com.google.gson.annotations.SerializedName
import ru.nstu.ordsys.order.domain.entity.OrderItemStatus

data class OrderItemForUpdateModel(
    @SerializedName("orderItemId")
    val orderItemId: Long,
    @SerializedName("cookId")
    val cookId: Long,
    @SerializedName("status")
    val status: OrderItemStatus
)