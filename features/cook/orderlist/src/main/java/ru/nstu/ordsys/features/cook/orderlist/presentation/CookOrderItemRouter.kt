package ru.nstu.ordsys.features.cook.orderlist.presentation

import ru.nstu.ordsys.features.cook.orderlist.domain.entity.DishForCook

interface CookOrderItemRouter {

    fun navigateToTechnologyScreen(dish: DishForCook)
}