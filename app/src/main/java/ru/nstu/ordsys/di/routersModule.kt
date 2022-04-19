package ru.nstu.ordsys.di

import org.koin.dsl.module
import ru.nstu.ordsys.features.dishes.menu.presentation.DishesListRouter
import ru.nstu.ordsys.navigation.router.DishesListRouterImpl

val routersModule = module {
    factory<DishesListRouter> { DishesListRouterImpl(router = get()) }
}