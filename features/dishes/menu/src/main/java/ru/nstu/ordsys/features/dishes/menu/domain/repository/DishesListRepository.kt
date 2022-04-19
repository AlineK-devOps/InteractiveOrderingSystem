package ru.nstu.ordsys.features.dishes.menu.domain.repository

import io.reactivex.Single
import ru.nstu.ordsys.shared.dishes.domain.entity.Dish

interface DishesListRepository {

    fun getSushiMenu(): Single<List<Dish>>

    fun getRollsMenu(): Single<List<Dish>>

    fun getHotRollsMenu(): Single<List<Dish>>

    fun getSnacksMenu(): Single<List<Dish>>

    fun getWokMenu(): Single<List<Dish>>

    fun getSoupsMenu(): Single<List<Dish>>

    fun getDrinksMenu(): Single<List<Dish>>

    fun getAdditionallyMenu(): Single<List<Dish>>
}