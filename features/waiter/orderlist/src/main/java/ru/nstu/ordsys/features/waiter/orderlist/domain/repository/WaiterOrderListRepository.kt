package ru.nstu.ordsys.features.waiter.orderlist.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import ru.nstu.ordsys.features.waiter.orderlist.domain.entity.OrderItemForUpdate
import ru.nstu.ordsys.features.waiter.orderlist.domain.entity.OrderListForWaiter

interface WaiterOrderListRepository {

    fun getOrders(): Single<List<OrderListForWaiter>>

    fun postDishStatus(itemUpdate: OrderItemForUpdate): Completable

    fun doneWaiterCalling(tableId: Long): Completable

    fun deleteDish(positionId: Long): Completable

    fun deleteOrder(tableId: Long): Completable

    fun doneOrder(orderId: Long): Completable
}