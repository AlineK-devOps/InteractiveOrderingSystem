package ru.nstu.ordsys.navigation.router

import com.github.terrakok.cicerone.Router
import ru.nstu.ordsys.features.dishes.menu.getDishesListScreen
import ru.nstu.ordsys.features.dishes.menu.presentation.DishesListRouter
import ru.nstu.ordsys.order.getOrderListScreen

class DishesListRouterImpl(private val router: Router) : DishesListRouter {

    override fun navigateToOrderListScreen() {
        router.navigateTo(getOrderListScreen())
    }
}