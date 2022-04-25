package ru.nstu.ordsys.order.domain.usecase

import io.reactivex.Completable
import ru.nstu.ordsys.order.domain.repository.OrderListRepository
import ru.nstu.ordsys.shared.dishes.domain.entity.Dish

class PostOrderListUseCase(private val repository: OrderListRepository) {

    operator fun invoke(order: HashMap<Dish, Int>): Completable =
        repository.postOrderList(order)
}