package ru.nstu.ordsys.navigation.router

import com.github.terrakok.cicerone.Router
import ru.nstu.ordsys.features.cook.orderlist.presentation.details.DishTechnologyRouter

class DishTechnologyRouterImpl(private val router: Router) : DishTechnologyRouter {

    override fun exit() {
        router.exit()
    }
}