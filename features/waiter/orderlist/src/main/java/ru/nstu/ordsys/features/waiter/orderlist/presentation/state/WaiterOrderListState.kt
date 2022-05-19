package ru.nstu.ordsys.features.waiter.orderlist.presentation.state

import ru.nstu.ordsys.features.waiter.orderlist.domain.entity.OrderListForWaiter

sealed class WaiterOrderListState {

    object Initial : WaiterOrderListState()

    object Loading : WaiterOrderListState()

    object Empty : WaiterOrderListState()

    data class Content(
        val ordersList: List<OrderListForWaiter>
    ) : WaiterOrderListState()

    object Error : WaiterOrderListState()
}