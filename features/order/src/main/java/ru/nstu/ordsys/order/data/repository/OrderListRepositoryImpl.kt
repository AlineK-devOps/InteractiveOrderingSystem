package ru.nstu.ordsys.order.data.repository

import io.reactivex.Completable
import ru.nstu.ordsys.order.data.datasource.OrderListDataSource
import ru.nstu.ordsys.order.data.mapper.toModel
import ru.nstu.ordsys.order.domain.repository.OrderListRepository
import ru.nstu.ordsys.shared.dishes.domain.entity.Dish

class OrderListRepositoryImpl(
    private val dataSource: OrderListDataSource
) : OrderListRepository {

    override fun postOrderList(order: HashMap<Dish, Int>): Completable =
        dataSource.postOrderList(order.toModel())
}