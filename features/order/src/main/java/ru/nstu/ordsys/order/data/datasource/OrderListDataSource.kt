package ru.nstu.ordsys.order.data.datasource

import io.reactivex.Completable
import ru.nstu.ordsys.order.data.model.OrderListModel

interface OrderListDataSource {

    fun postOrderList(orderModel: OrderListModel): Completable
}