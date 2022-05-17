package ru.nstu.ordsys.features.cook.orderlist.domain.entity

data class OrderListForCook(
    val tableId: Long,
    val order: List<OrderItemForCook>
)