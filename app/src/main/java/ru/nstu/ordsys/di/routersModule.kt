package ru.nstu.ordsys.di

import org.koin.dsl.module
import ru.nstu.ordsys.features.dishes.menu.presentation.DishesMenuRouter
import ru.nstu.ordsys.navigation.router.DishesMenuRouterImpl

val routersModule = module {
    factory<DishesMenuRouter> { DishesMenuRouterImpl(router = get()) }
}