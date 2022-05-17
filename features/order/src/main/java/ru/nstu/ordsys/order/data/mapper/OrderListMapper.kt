package ru.nstu.ordsys.order.data.mapper

import ru.nstu.ordsys.order.data.model.OrderItemModel
import ru.nstu.ordsys.order.data.model.OrderListModel
import ru.nstu.ordsys.shared.dishes.domain.entity.Dish

fun HashMap<Dish, Int>.toModel(): OrderListModel =
    OrderListModel(
        2,
        map { orderItem -> OrderItemModel(orderItem.key.id, orderItem.value) }
    )