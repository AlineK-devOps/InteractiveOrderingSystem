package ru.nstu.ordsys.navigation.router

import com.github.terrakok.cicerone.Router
import ru.nstu.ordsys.features.dishes.menu.getDishesListScreen
import ru.nstu.ordsys.order.presentation.OrderListRouter

class OrderListRouterImpl(private val router: Router) : OrderListRouter {

    override fun navigateToDishesListScreen(position: Int) {
        router.navigateTo(getDishesListScreen(position))
    }
}