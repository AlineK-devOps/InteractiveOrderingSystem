package ru.nstu.ordsys.features.cook.orderlist.data.mapper

import ru.nstu.ordsys.features.cook.orderlist.data.model.OrderItemForUpdateModel
import ru.nstu.ordsys.features.cook.orderlist.domain.entity.OrderItemForUpdate

fun OrderItemForUpdate.toModel(): OrderItemForUpdateModel =
    OrderItemForUpdateModel(
        orderItemId = orderItemId,
        cookId = cookId,
        status = status
    )