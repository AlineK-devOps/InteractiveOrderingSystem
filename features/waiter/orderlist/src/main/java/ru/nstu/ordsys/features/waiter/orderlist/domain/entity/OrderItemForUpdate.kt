package ru.nstu.ordsys.features.waiter.orderlist.domain.entity

import com.google.gson.annotations.SerializedName
import ru.nstu.ordsys.order.domain.entity.OrderItemStatus

data class OrderItemForUpdate(
    val orderItemId: Long,
    val waiterId: Long,
    val status: OrderItemStatus
)