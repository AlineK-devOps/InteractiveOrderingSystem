package ru.nstu.ordsys.waiter.domain.usecase

import io.reactivex.Completable
import ru.nstu.ordsys.waiter.domain.repository.WaiterCallRepository

class PostWaiterCallingUseCase(private val repository: WaiterCallRepository) {

    operator fun invoke(tableId: Long) : Completable =
        repository.postWaiterCalling(tableId)
}