package ru.nstu.ordsys.features.cook.orderlist.domain.entity

import ru.nstu.ordsys.order.domain.entity.OrderItemStatus
import java.sql.Timestamp

data class OrderItemForCook(
    val id: Long,
    val dish: DishForCook,
    var status: OrderItemStatus,
    val orderTime: Timestamp,
    var cook: String?
)