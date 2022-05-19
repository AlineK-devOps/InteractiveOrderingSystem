package ru.nstu.ordsys.features.waiter.orderlist.data.model

import com.google.gson.annotations.SerializedName
import ru.nstu.ordsys.order.domain.entity.OrderItemStatus

data class OrderItemForUpdateModel(
    @SerializedName("orderItemId")
    val orderItemId: Long,
    @SerializedName("waiterId")
    val waiterId: Long,
    @SerializedName("status")
    val status: OrderItemStatus
)