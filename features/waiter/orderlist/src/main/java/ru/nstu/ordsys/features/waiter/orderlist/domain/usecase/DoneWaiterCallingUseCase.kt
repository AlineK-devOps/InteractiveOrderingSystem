package ru.nstu.ordsys.features.waiter.orderlist.domain.usecase

import io.reactivex.Completable
import ru.nstu.ordsys.features.waiter.orderlist.domain.repository.WaiterOrderListRepository

class DoneWaiterCallingUseCase(private val repository: WaiterOrderListRepository) {

    operator fun invoke(tableId: Long): Completable =
        repository.doneWaiterCalling(tableId)
}