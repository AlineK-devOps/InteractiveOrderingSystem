package ru.nstu.ordsys.features.cook.orderlist.data.datasource

import io.reactivex.Single
import ru.nstu.ordsys.features.cook.orderlist.data.api.CookOrderListApi
import ru.nstu.ordsys.features.cook.orderlist.data.model.OrderListForCookModel

class CookOrderListDataSourceImpl(private val api: CookOrderListApi) : CookOrderListDataSource  {

    override fun getOrders(): Single<List<OrderListForCookModel>> =
        api.getOrders()

    override fun getHotWorkshopOrders(): Single<List<OrderListForCookModel>> =
        api.getHotWorkshopOrders()

    override fun getColdWorkshopOrders(): Single<List<OrderListForCookModel>> =
        api.getColdWorkshopOrders()

    override fun getBarOrders(): Single<List<OrderListForCookModel>> =
        api.getBarOrders()
}