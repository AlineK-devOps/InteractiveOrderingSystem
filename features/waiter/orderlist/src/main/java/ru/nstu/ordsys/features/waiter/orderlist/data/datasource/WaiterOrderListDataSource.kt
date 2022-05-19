package ru.nstu.ordsys.features.waiter.orderlist.data.datasource

import io.reactivex.Completable
import io.reactivex.Single
import ru.nstu.ordsys.features.waiter.orderlist.data.model.OrderItemForUpdateModel
import ru.nstu.ordsys.features.waiter.orderlist.data.model.OrderListForWaiterModel

interface WaiterOrderListDataSource {

    fun getOrders(): Single<List<OrderListForWaiterModel>>

    fun postDishStatus(itemUpdate: OrderItemForUpdateModel): Completable

    fun doneWaiterCalling(tableId: Long): Completable

    fun deleteDish(positionId: Long): Completable

    fun deleteOrder(tableId: Long): Completable

    fun doneOrder(orderId: Long): Completable
}