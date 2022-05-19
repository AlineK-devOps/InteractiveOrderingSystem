package ru.nstu.ordsys.navigation

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import ru.nstu.ordsys.features.waiter.orderlist.getWaiterOrderListScreen

fun buildCicerone(): Cicerone<Router> =
    Cicerone.create().apply {
        router.newRootScreen(getWaiterOrderListScreen())
    }