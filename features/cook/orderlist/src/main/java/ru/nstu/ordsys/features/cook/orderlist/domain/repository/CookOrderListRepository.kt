package ru.nstu.ordsys.features.cook.orderlist.domain.repository

import io.reactivex.Completable
import io.reactivex.Single
import ru.nstu.ordsys.features.cook.orderlist.domain.entity.OrderItemForUpdate
import ru.nstu.ordsys.features.cook.orderlist.domain.entity.OrderListForCook

interface CookOrderListRepository {

    fun getOrders(): Single<List<OrderListForCook>>

    fun getHotWorkshopOrders(): Single<List<OrderListForCook>>

    fun getColdWorkshopOrders(): Single<List<OrderListForCook>>

    fun getBarOrders(): Single<List<OrderListForCook>>

    fun postDishStatus(itemUpdate: OrderItemForUpdate): Completable
}