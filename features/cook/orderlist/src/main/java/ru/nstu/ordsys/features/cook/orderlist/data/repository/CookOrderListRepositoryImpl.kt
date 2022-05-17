package ru.nstu.ordsys.features.cook.orderlist.data.repository

import io.reactivex.Single
import ru.nstu.ordsys.features.cook.orderlist.data.datasource.CookOrderListDataSource
import ru.nstu.ordsys.features.cook.orderlist.data.mapper.toEntity
import ru.nstu.ordsys.features.cook.orderlist.data.model.OrderListForCookModel
import ru.nstu.ordsys.features.cook.orderlist.domain.entity.OrderListForCook
import ru.nstu.ordsys.features.cook.orderlist.domain.repository.CookOrderListRepository

class CookOrderListRepositoryImpl(private val datasource: CookOrderListDataSource) :
    CookOrderListRepository {

    override fun getOrders(): Single<List<OrderListForCook>> =
        datasource.getOrders().map(List<OrderListForCookModel>::toEntity)

    override fun getHotWorkshopOrders(): Single<List<OrderListForCook>> =
        datasource.getHotWorkshopOrders().map(List<OrderListForCookModel>::toEntity)

    override fun getColdWorkshopOrders(): Single<List<OrderListForCook>> =
        datasource.getColdWorkshopOrders().map(List<OrderListForCookModel>::toEntity)

    override fun getBarOrders(): Single<List<OrderListForCook>> =
        datasource.getBarOrders().map(List<OrderListForCookModel>::toEntity)
}