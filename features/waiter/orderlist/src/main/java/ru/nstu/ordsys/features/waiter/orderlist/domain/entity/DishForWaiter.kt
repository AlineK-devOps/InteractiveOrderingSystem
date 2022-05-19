package ru.nstu.ordsys.features.waiter.orderlist.domain.entity

data class DishForWaiter(
    val name: String,
    val weight: Int,
    val cookingTime: Int
)