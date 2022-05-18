package ru.nstu.ordsys.features.cook.orderlist.data.mapper

import ru.nstu.ordsys.features.cook.orderlist.data.model.DishForCookModel
import ru.nstu.ordsys.features.cook.orderlist.data.model.OrderItemForCookModel
import ru.nstu.ordsys.features.cook.orderlist.data.model.OrderListForCookModel
import ru.nstu.ordsys.features.cook.orderlist.domain.entity.DishForCook
import ru.nstu.ordsys.features.cook.orderlist.domain.entity.OrderItemForCook
import ru.nstu.ordsys.features.cook.orderlist.domain.entity.OrderListForCook
import ru.nstu.ordsys.order.domain.entity.OrderItemStatus


fun List<OrderListForCookModel>.toEntity(): List<OrderListForCook> =
    map { it.toEntity() }

fun OrderListForCookModel.toEntity(): OrderListForCook =
    OrderListForCook(
        tableId,
        order.convertToEntity()
    )

fun List<OrderItemForCookModel>.convertToEntity(): List<OrderItemForCook> =
    map { it.toEntity() }

fun OrderItemForCookModel.toEntity(): OrderItemForCook =
    OrderItemForCook(
        id,
        dish.toEntity(),
        convertStatus(status),
        orderTime,
        cook
    )

fun DishForCookModel.toEntity(): DishForCook =
    DishForCook(
        name,
        weight,
        cookingTime,
        imageUrl,
        recipe
    )

fun convertStatus(status: String): OrderItemStatus =
    when (status) {
        "IN_COOKING_PROCESS"   -> OrderItemStatus.IN_COOKING_PROCESS
        "IN_QUEUE_FOR_SERVING" -> OrderItemStatus.IN_QUEUE_FOR_SERVING
        "SERVED"               -> OrderItemStatus.SERVED
        else                   -> OrderItemStatus.IN_QUEUE_FOR_COOKING
    }