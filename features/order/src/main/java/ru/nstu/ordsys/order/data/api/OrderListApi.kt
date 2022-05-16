package ru.nstu.ordsys.order.data.api

import io.reactivex.Completable
import retrofit2.http.Body
import retrofit2.http.POST
import ru.nstu.ordsys.order.data.model.OrderListModel

interface OrderListApi {

    @POST("/api/order")
    fun postOrderList(@Body orderModel: OrderListModel): Completable
}