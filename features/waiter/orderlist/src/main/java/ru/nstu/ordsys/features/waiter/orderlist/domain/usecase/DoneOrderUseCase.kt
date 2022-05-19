package ru.nstu.ordsys.features.waiter.orderlist.domain.usecase

import io.reactivex.Completable
import ru.nstu.ordsys.features.waiter.orderlist.domain.repository.WaiterOrderListRepository

class DoneOrderUseCase(private val repository: WaiterOrderListRepository) {

    operator fun invoke(orderId: Long): Completable =
        repository.doneOrder(orderId)
}