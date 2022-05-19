package ru.nstu.ordsys.features.waiter.orderlist.domain.usecase

import io.reactivex.Completable
import ru.nstu.ordsys.features.waiter.orderlist.domain.repository.WaiterOrderListRepository

class DeleteDishUseCase(private val repository: WaiterOrderListRepository) {

    operator fun invoke(positionId: Long): Completable =
        repository.deleteDish(positionId)
}