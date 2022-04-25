package ru.nstu.ordsys.order.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.nstu.ordsys.component.ui.mvvm.BaseViewModel
import ru.nstu.ordsys.order.domain.entity.Order
import ru.nstu.ordsys.order.presentation.state.OrderListState

class OrderListViewModel(
    private val router: OrderListRouter
) : BaseViewModel() {

    private var _state = MutableLiveData<OrderListState>(OrderListState.Initial)
    val state: LiveData<OrderListState> = _state

    fun loadOrderList(){
        _state.value = OrderListState.Content(Order.order)
    }
}