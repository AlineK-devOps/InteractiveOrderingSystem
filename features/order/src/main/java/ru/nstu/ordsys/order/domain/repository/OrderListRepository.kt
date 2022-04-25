package ru.nstu.ordsys.order.domain.repository

import io.reactivex.Completable
import ru.nstu.ordsys.shared.dishes.domain.entity.Dish

interface OrderListRepository {

    fun postOrderList(order: HashMap<Dish, Int>): Completable
}