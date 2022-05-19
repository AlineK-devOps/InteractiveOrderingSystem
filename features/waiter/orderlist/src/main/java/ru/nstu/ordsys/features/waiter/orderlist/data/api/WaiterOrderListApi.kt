package ru.nstu.ordsys.features.waiter.orderlist.data.api

import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*
import ru.nstu.ordsys.features.waiter.orderlist.data.model.OrderItemForUpdateModel
import ru.nstu.ordsys.features.waiter.orderlist.data.model.OrderListForWaiterModel

interface WaiterOrderListApi {

    @GET("/api/waiter/orders")
    fun getOrders(): Single<List<OrderListForWaiterModel>>

    @POST("/api/waiter/update_status")
    fun postDishStatus(@Body itemUpdate: OrderItemForUpdateModel): Completable

    @POST("/api/waiter/calling/{tableId}")
    fun doneWaiterCalling(@Path("tableId") tableId: Long): Completable

    @POST("/api/waiter/done_order/{orderId}")
    fun doneOrder(@Path("orderId") orderId: Long): Completable

    @DELETE("/api/waiter/delete/dish/{positionId}")
    fun  deleteDish(@Path("positionId") positionId: Long): Completable

    @DELETE("/api/waiter/delete/order/{tableId}")
    fun  deleteOrder(@Path("tableId") tableId: Long): Completable
}