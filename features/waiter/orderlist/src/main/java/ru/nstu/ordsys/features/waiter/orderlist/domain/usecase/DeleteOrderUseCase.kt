package ru.nstu.ordsys.features.waiter.orderlist.domain.usecase

import io.reactivex.Completable
import ru.nstu.ordsys.features.waiter.orderlist.domain.repository.WaiterOrderListRepository

class DeleteOrderUseCase(private val repository: WaiterOrderListRepository) {

    operator fun invoke(tableId: Long): Completable =
        repository.deleteOrder(tableId)
}