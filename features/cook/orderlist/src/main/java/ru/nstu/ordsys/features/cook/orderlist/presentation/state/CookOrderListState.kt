package ru.nstu.ordsys.features.cook.orderlist.presentation.state

import ru.nstu.ordsys.features.cook.orderlist.domain.entity.OrderListForCook

sealed class CookOrderListState {

    object Initial : CookOrderListState()

    object Loading : CookOrderListState()

    object Empty: CookOrderListState()

    data class Content(
        val ordersList: List<OrderListForCook>
    ) : CookOrderListState()

    object Error : CookOrderListState()
}