package ru.nstu.ordsys.order.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import ru.nstu.ordsys.component.ui.mvvm.BaseViewModel
import ru.nstu.ordsys.order.domain.entity.Order
import ru.nstu.ordsys.order.domain.usecase.PostOrderListUseCase
import ru.nstu.ordsys.order.presentation.state.OrderListState

class OrderListViewModel(
    private val useCase: PostOrderListUseCase,
    private val router: OrderListRouter
) : BaseViewModel() {

    private var _state = MutableLiveData<OrderListState>(OrderListState.Initial)
    val state: LiveData<OrderListState> = _state

    fun sendOrder() {
        _state.value = OrderListState.Loading

        useCase(Order.order)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = {
                    //_state.value = OrderListState.SendOperationSuccess
                    clearCart()
                    loadOrderList()
                },
                onError = {
                    _state.value = OrderListState.Error
                }
            )
            .addToDisposableList()
    }

    fun loadOrderList() {
        _state.value = OrderListState.Content(Order.order)
    }

    fun clearCart() {
        Order.clearOrder()
    }

    fun navigateToDishesListScreen(position: Int) {
        router.navigateToDishesListScreen(position)
    }

    fun navigateToBillListScreen(){
        router.navigateToBillListScreen()
    }
}