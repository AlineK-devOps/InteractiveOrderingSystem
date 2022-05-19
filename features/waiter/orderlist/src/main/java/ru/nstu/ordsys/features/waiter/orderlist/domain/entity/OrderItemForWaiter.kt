package ru.nstu.ordsys.features.waiter.orderlist.domain.entity

import ru.nstu.ordsys.order.domain.entity.OrderItemStatus
import java.sql.Timestamp

data class OrderItemForWaiter(
    val id: Long,
    val dish: DishForWaiter,
    var status: OrderItemStatus,
    val orderTime: Timestamp,
    var cook: String?,
    var waiter: String?
)