package ru.nstu.ordsys.features.cook.orderlist.data.datasource

import io.reactivex.Single
import ru.nstu.ordsys.features.cook.orderlist.data.model.OrderListForCookModel

interface CookOrderListDataSource {

    fun getOrders(): Single<List<OrderListForCookModel>>

    fun getHotWorkshopOrders(): Single<List<OrderListForCookModel>>

    fun getColdWorkshopOrders(): Single<List<OrderListForCookModel>>

    fun getBarOrders(): Single<List<OrderListForCookModel>>
}