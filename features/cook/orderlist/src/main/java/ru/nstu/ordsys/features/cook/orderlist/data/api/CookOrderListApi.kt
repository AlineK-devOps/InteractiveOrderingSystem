package ru.nstu.ordsys.features.cook.orderlist.data.api

import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import ru.nstu.ordsys.features.cook.orderlist.data.model.OrderItemForUpdateModel
import ru.nstu.ordsys.features.cook.orderlist.data.model.OrderListForCookModel

interface CookOrderListApi {

    @GET("/api/cook/orders")
    fun getOrders(): Single<List<OrderListForCookModel>>

    @GET("/api/cook/orders/hot_workshop")
    fun getHotWorkshopOrders(): Single<List<OrderListForCookModel>>

    @GET("/api/cook/orders/cold_workshop")
    fun getColdWorkshopOrders(): Single<List<OrderListForCookModel>>

    @GET("/api/cook/orders/bar")
    fun getBarOrders(): Single<List<OrderListForCookModel>>

    @POST("/api/cook/update_status")
    fun postDishStatus(@Body itemUpdate: OrderItemForUpdateModel): Completable
}