package ru.nstu.ordsys.features.bill.presentation.state

import ru.nstu.ordsys.shared.dishes.domain.entity.Dish

sealed class OrderListState {

    object Initial : OrderListState()

    object Loading : OrderListState()

    data class Content(
        val order: HashMap<Dish, Int>
    ) : OrderListState()

    object Error : OrderListState()
}