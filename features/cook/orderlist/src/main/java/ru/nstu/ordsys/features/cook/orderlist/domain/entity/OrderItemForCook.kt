package ru.nstu.ordsys.features.cook.orderlist.domain.entity

import ru.nstu.ordsys.order.domain.entity.OrderItemStatus

data class OrderItemForCook(
    val dish: DishForCook,
    val status: OrderItemStatus
)