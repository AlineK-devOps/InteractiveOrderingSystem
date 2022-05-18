package ru.nstu.ordsys.features.cook.orderlist.domain.entity

import ru.nstu.ordsys.order.domain.entity.OrderItemStatus

data class OrderItemForUpdate(
    val orderItemId: Long,
    val cookId: Long,
    val status: OrderItemStatus
)