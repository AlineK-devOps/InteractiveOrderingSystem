package ru.nstu.ordsys.features.dishes.menu.data.datasource

import io.reactivex.Single
import ru.nstu.ordsys.features.dishes.menu.data.api.DishesMenuApi
import ru.nstu.ordsys.shared.dishes.data.model.DishModel

class DishesMenuDatasourceImpl(private val api: DishesMenuApi) : DishesMenuDatasource {

    override fun getSushiMenu(): Single<List<DishModel>> =
        api.getSushiMenu()

    override fun getRollsMenu(): Single<List<DishModel>> =
        api.getRollsMenu()

    override fun getHotRollsMenu(): Single<List<DishModel>> =
        api.getHotRollsMenu()

    override fun getSnacksMenu(): Single<List<DishModel>> =
        api.getSnacksMenu()

    override fun getWokMenu(): Single<List<DishModel>> =
        api.getWokMenu()

    override fun getSoupsMenu(): Single<List<DishModel>> =
        api.getSoupsMenu()

    override fun getDrinksMenu(): Single<List<DishModel>> =
        api.getDrinksMenu()

    override fun getAdditionallyMenu(): Single<List<DishModel>> =
        api.getAdditionallyMenu()
}