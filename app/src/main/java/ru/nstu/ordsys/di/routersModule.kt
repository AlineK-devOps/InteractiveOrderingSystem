package ru.nstu.ordsys.di

import org.koin.dsl.module
import ru.nstu.ordsys.features.bill.presentation.BillListRouter
import ru.nstu.ordsys.features.cook.orderlist.presentation.CookOrderItemRouter
import ru.nstu.ordsys.features.cook.orderlist.presentation.CookOrderListRouter
import ru.nstu.ordsys.features.cook.orderlist.presentation.details.DishTechnologyRouter
import ru.nstu.ordsys.features.dishes.menu.presentation.DishesListRouter
import ru.nstu.ordsys.navigation.router.*
import ru.nstu.ordsys.order.presentation.OrderListRouter

val routersModule = module {
    factory<DishesListRouter> { DishesListRouterImpl(router = get()) }
    factory<OrderListRouter> { OrderListRouterImpl(router = get()) }
    factory<BillListRouter> { BillListRouterImpl(router = get()) }
    factory<CookOrderListRouter> { CookOrderListRouterImpl(router = get()) }
    factory<CookOrderItemRouter> { CookOrderItemRouterImpl(router = get()) }
    factory<DishTechnologyRouter> { DishTechnologyRouterImpl(router = get()) }
}