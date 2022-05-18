package ru.nstu.ordsys.features.cook.orderlist.domain.usecase

import io.reactivex.Completable
import ru.nstu.ordsys.features.cook.orderlist.domain.entity.OrderItemForUpdate
import ru.nstu.ordsys.features.cook.orderlist.domain.repository.CookOrderListRepository

class PostDishStatusUseCase(private val repository: CookOrderListRepository) {

    operator fun invoke(itemUpdate: OrderItemForUpdate): Completable =
        repository.postDishStatus(itemUpdate)
}