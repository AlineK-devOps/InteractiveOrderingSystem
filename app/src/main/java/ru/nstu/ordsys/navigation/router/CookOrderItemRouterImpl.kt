package ru.nstu.ordsys.navigation.router

import com.github.terrakok.cicerone.Router
import ru.nstu.ordsys.features.cook.orderlist.domain.entity.DishForCook
import ru.nstu.ordsys.features.cook.orderlist.getTechnologyScreen
import ru.nstu.ordsys.features.cook.orderlist.presentation.CookOrderItemRouter

class CookOrderItemRouterImpl(private val router: Router) : CookOrderItemRouter {

    override fun navigateToTechnologyScreen(dish: DishForCook) {
        router.navigateTo(getTechnologyScreen(dish))
    }
}