package ru.nstu.ordsys.navigation.router

import com.github.terrakok.cicerone.Router
import ru.nstu.ordsys.features.bill.presentation.BillListRouter
import ru.nstu.ordsys.features.dishes.menu.getDishesListScreen
import ru.nstu.ordsys.order.getOrderListScreen

class BillListRouterImpl(private val router: Router) : BillListRouter {

    override fun navigateToOrderListScreen() {
        router.navigateTo(getOrderListScreen())
    }

    override fun navigateToDishesListScreen(position: Int) {
        router.navigateTo(getDishesListScreen(position))
    }
}