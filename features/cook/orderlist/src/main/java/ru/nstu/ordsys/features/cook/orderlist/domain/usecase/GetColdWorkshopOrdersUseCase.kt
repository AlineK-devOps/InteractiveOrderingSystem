package ru.nstu.ordsys.features.cook.orderlist.domain.usecase

import io.reactivex.Single
import ru.nstu.ordsys.features.cook.orderlist.domain.entity.OrderListForCook
import ru.nstu.ordsys.features.cook.orderlist.domain.repository.CookOrderListRepository

class GetColdWorkshopOrdersUseCase(private val repository: CookOrderListRepository) {

    operator fun invoke(): Single<List<OrderListForCook>> =
        repository.getColdWorkshopOrders()
}