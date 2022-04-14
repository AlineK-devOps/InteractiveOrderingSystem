package ru.nstu.ordsys.features.dishes.menu.data.datasource

import io.reactivex.Single
import ru.nstu.ordsys.shared.dishes.data.model.DishModel

interface DishesMenuDatasource {

    fun getSushiMenu(): Single<List<DishModel>>

    fun getRollsMenu(): Single<List<DishModel>>

    fun getHotRollsMenu(): Single<List<DishModel>>

    fun getSnacksMenu(): Single<List<DishModel>>

    fun getWokMenu(): Single<List<DishModel>>

    fun getSoupsMenu(): Single<List<DishModel>>

    fun getDrinksMenu(): Single<List<DishModel>>

    fun getAdditionallyMenu(): Single<List<DishModel>>
}