package ru.nstu.ordsys.features.waiter.orderlist.data.repository

import io.reactivex.Completable
import io.reactivex.Single
import ru.nstu.ordsys.features.waiter.orderlist.data.datasource.WaiterOrderListDataSource
import ru.nstu.ordsys.features.waiter.orderlist.data.mapper.toEntity
import ru.nstu.ordsys.features.waiter.orderlist.data.mapper.toModel
import ru.nstu.ordsys.features.waiter.orderlist.data.model.OrderListForWaiterModel
import ru.nstu.ordsys.features.waiter.orderlist.domain.entity.OrderItemForUpdate
import ru.nstu.ordsys.features.waiter.orderlist.domain.entity.OrderListForWaiter
import ru.nstu.ordsys.features.waiter.orderlist.domain.repository.WaiterOrderListRepository

class WaiterOrderListRepositoryImpl(private val datasource: WaiterOrderListDataSource) :
    WaiterOrderListRepository {

    override fun getOrders(): Single<List<OrderListForWaiter>> =
        datasource.getOrders().map(List<OrderListForWaiterModel>::toEntity)

    override fun postDishStatus(itemUpdate: OrderItemForUpdate): Completable =
        datasource.postDishStatus(itemUpdate.toModel())

    override fun doneWaiterCalling(tableId: Long): Completable =
        datasource.doneWaiterCalling(tableId)

    override fun deleteDish(positionId: Long): Completable =
        datasource.deleteDish(positionId)

    override fun deleteOrder(tableId: Long): Completable =
        datasource.deleteOrder(tableId)

    override fun doneOrder(tableId: Long): Completable =
        datasource.doneOrder(tableId)
}