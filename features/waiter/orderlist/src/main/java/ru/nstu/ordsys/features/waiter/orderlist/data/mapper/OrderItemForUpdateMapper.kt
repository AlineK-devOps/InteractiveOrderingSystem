package ru.nstu.ordsys.features.waiter.orderlist.data.mapper

import ru.nstu.ordsys.features.waiter.orderlist.data.model.OrderItemForUpdateModel
import ru.nstu.ordsys.features.waiter.orderlist.domain.entity.OrderItemForUpdate

fun OrderItemForUpdate.toModel(): OrderItemForUpdateModel =
    OrderItemForUpdateModel(
        orderItemId = orderItemId,
        waiterId = waiterId,
        status = status
    )