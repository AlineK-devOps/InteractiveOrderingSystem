package ru.nstu.ordsys.features.waiter.orderlist.domain.entity

data class OrderListForWaiter(
    val tableId: Long,
    val order: List<OrderItemForWaiter>,
    var waiterCalled: Boolean
)