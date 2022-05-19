package ru.nstu.ordsys.features.waiter.orderlist.data.mapper

import ru.nstu.ordsys.features.waiter.orderlist.data.model.DishForWaiterModel
import ru.nstu.ordsys.features.waiter.orderlist.data.model.OrderItemForWaiterModel
import ru.nstu.ordsys.features.waiter.orderlist.data.model.OrderListForWaiterModel
import ru.nstu.ordsys.features.waiter.orderlist.domain.entity.DishForWaiter
import ru.nstu.ordsys.features.waiter.orderlist.domain.entity.OrderItemForWaiter
import ru.nstu.ordsys.features.waiter.orderlist.domain.entity.OrderListForWaiter
import ru.nstu.ordsys.order.domain.entity.OrderItemStatus

fun List<OrderListForWaiterModel>.toEntity(): List<OrderListForWaiter> =
    map { it.toEntity() }

fun OrderListForWaiterModel.toEntity(): OrderListForWaiter =
    OrderListForWaiter(
        tableId,
        order.convertToEntity(),
        waiterCalled
    )

fun List<OrderItemForWaiterModel>.convertToEntity(): List<OrderItemForWaiter> =
    map { it.toEntity() }

fun OrderItemForWaiterModel.toEntity(): OrderItemForWaiter =
    OrderItemForWaiter(
        id,
        dish.toEntity(),
        convertStatus(status),
        orderTime,
        cook,
        waiter
    )

fun DishForWaiterModel.toEntity(): DishForWaiter =
    DishForWaiter(
        name,
        weight,
        cookingTime
    )

fun convertStatus(status: String): OrderItemStatus =
    when (status) {
        "IN_COOKING_PROCESS" -> OrderItemStatus.IN_COOKING_PROCESS
        "IN_QUEUE_FOR_SERVING" -> OrderItemStatus.IN_QUEUE_FOR_SERVING
        "SERVED" -> OrderItemStatus.SERVED
        else -> OrderItemStatus.IN_QUEUE_FOR_COOKING
    }