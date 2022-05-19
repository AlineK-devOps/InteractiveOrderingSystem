package ru.nstu.ordsys.features.waiter.orderlist.domain.usecase

import io.reactivex.Single
import ru.nstu.ordsys.features.waiter.orderlist.domain.entity.OrderListForWaiter
import ru.nstu.ordsys.features.waiter.orderlist.domain.repository.WaiterOrderListRepository

class GetOrdersUseCase(private val repository: WaiterOrderListRepository) {

    operator fun invoke(): Single<List<OrderListForWaiter>> =
        repository.getOrders()
}