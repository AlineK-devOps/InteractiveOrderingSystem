package ru.nstu.ordsys.features.dishes.menu.data.api

import io.reactivex.Single
import retrofit2.http.GET
import ru.nstu.ordsys.shared.dishes.data.model.DishModel

interface DishesMenuApi {

    @GET("/api/dishes/sushi")
    fun getSushiMenu(): Single<List<DishModel>>

    @GET("/api/dishes/rolls")
    fun getRollsMenu(): Single<List<DishModel>>

    @GET("/api/dishes/hot_rolls")
    fun getHotRollsMenu(): Single<List<DishModel>>

    @GET("/api/dishes/snacks")
    fun getSnacksMenu(): Single<List<DishModel>>

    @GET("/api/dishes/wok")
    fun getWokMenu(): Single<List<DishModel>>

    @GET("/api/dishes/soups")
    fun getSoupsMenu(): Single<List<DishModel>>

    @GET("/api/dishes/drinks")
    fun getDrinksMenu(): Single<List<DishModel>>

    @GET("/api/dishes/additionally")
    fun getAdditionallyMenu(): Single<List<DishModel>>
}