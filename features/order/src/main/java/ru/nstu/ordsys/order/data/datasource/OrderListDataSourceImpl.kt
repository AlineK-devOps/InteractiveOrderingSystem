package ru.nstu.ordsys.order.data.datasource

import io.reactivex.Completable
import ru.nstu.ordsys.order.data.api.OrderListApi
import ru.nstu.ordsys.order.data.model.OrderListModel

class OrderListDataSourceImpl(
    private val api: OrderListApi
) : OrderListDataSource {

    override fun postOrderList(orderModel: OrderListModel): Completable =
        api.postOrderList(orderModel)
}