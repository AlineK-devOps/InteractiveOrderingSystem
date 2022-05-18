package ru.nstu.ordsys.order.data.mapper

import ru.nstu.ordsys.order.data.model.OrderItemModel
import ru.nstu.ordsys.order.data.model.OrderListModel
import ru.nstu.ordsys.shared.dishes.domain.entity.Dish
import ru.nstu.ordsys.shared.user.entity.User

fun HashMap<Dish, Int>.toModel(): OrderListModel =
    OrderListModel(
        User.getId(),
        map { orderItem -> OrderItemModel(orderItem.key.id, orderItem.value) }
    )