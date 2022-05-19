package ru.nstu.ordsys.features.waiter.orderlist.data.datasource

import io.reactivex.Completable
import io.reactivex.Single
import ru.nstu.ordsys.features.waiter.orderlist.data.api.WaiterOrderListApi
import ru.nstu.ordsys.features.waiter.orderlist.data.model.OrderItemForUpdateModel
import ru.nstu.ordsys.features.waiter.orderlist.data.model.OrderListForWaiterModel

class WaiterOrderListDataSourceImpl(private val api: WaiterOrderListApi) :
    WaiterOrderListDataSource {

    override fun getOrders(): Single<List<OrderListForWaiterModel>> =
        api.getOrders()

    override fun postDishStatus(itemUpdate: OrderItemForUpdateModel): Completable =
        api.postDishStatus(itemUpdate)

    override fun doneWaiterCalling(tableId: Long): Completable =
        api.doneWaiterCalling(tableId)

    override fun deleteDish(positionId: Long): Completable =
        api.deleteDish(positionId)

    override fun deleteOrder(tableId: Long): Completable =
        api.deleteOrder(tableId)

    override fun doneOrder(orderId: Long): Completable =
        api.doneOrder(orderId)
}