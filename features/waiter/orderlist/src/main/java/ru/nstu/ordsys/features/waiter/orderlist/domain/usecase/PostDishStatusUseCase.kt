package ru.nstu.ordsys.features.waiter.orderlist.domain.usecase

import io.reactivex.Completable
import ru.nstu.ordsys.features.waiter.orderlist.domain.entity.OrderItemForUpdate
import ru.nstu.ordsys.features.waiter.orderlist.domain.repository.WaiterOrderListRepository

class PostDishStatusUseCase(private val repository: WaiterOrderListRepository) {

    operator fun invoke(itemUpdate: OrderItemForUpdate): Completable =
        repository.postDishStatus(itemUpdate)
}