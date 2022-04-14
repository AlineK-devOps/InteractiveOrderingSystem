package ru.nstu.ordsys.features.dishes.menu.data.repository

import io.reactivex.Single
import ru.nstu.ordsys.features.dishes.menu.data.datasource.DishesMenuDatasource
import ru.nstu.ordsys.features.dishes.menu.data.mapper.toEntity
import ru.nstu.ordsys.features.dishes.menu.domain.repository.DishesMenuRepository
import ru.nstu.ordsys.shared.dishes.data.model.DishModel
import ru.nstu.ordsys.shared.dishes.domain.entity.Dish

class DishesMenuRepositoryImpl(private val datasource: DishesMenuDatasource): DishesMenuRepository {

    override fun getSushiMenu(): Single<List<Dish>> =
        datasource.getSushiMenu().map(List<DishModel>::toEntity)

    override fun getRollsMenu(): Single<List<Dish>> =
        datasource.getRollsMenu().map(List<DishModel>::toEntity)

    override fun getHotRollsMenu(): Single<List<Dish>> =
        datasource.getHotRollsMenu().map(List<DishModel>::toEntity)

    override fun getSnacksMenu(): Single<List<Dish>> =
        datasource.getSnacksMenu().map(List<DishModel>::toEntity)

    override fun getWokMenu(): Single<List<Dish>> =
        datasource.getWokMenu().map(List<DishModel>::toEntity)

    override fun getSoupsMenu(): Single<List<Dish>> =
        datasource.getSoupsMenu().map(List<DishModel>::toEntity)

    override fun getDrinksMenu(): Single<List<Dish>> =
        datasource.getDrinksMenu().map(List<DishModel>::toEntity)

    override fun getAdditionallyMenu(): Single<List<Dish>> =
        datasource.getAdditionallyMenu().map(List<DishModel>::toEntity)
}