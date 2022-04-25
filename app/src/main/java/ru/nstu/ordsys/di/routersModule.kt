package ru.nstu.ordsys.di

import org.koin.dsl.module
import ru.nstu.ordsys.features.dishes.menu.presentation.DishesListRouter
import ru.nstu.ordsys.navigation.router.DishesListRouterImpl
import ru.nstu.ordsys.navigation.router.OrderListRouterImpl
import ru.nstu.ordsys.order.presentation.OrderListRouter

val routersModule = module {
    factory<DishesListRouter> { DishesListRouterImpl(router = get()) }
    factory<OrderListRouter> { OrderListRouterImpl(router = get()) }
}