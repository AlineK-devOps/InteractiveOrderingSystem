package ru.nstu.ordsys.order.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import ru.nstu.ordsys.component.ui.mvvm.BaseViewModel
import ru.nstu.ordsys.features.dishes.menu.presentation.state.DishesListState
import ru.nstu.ordsys.order.domain.entity.Order
import ru.nstu.ordsys.order.domain.usecase.PostOrderListUseCase
import ru.nstu.ordsys.order.presentation.state.OrderListState
import ru.nstu.ordsys.shared.user.entity.User
import ru.nstu.ordsys.waiter.domain.usecase.PostWaiterCallingUseCase

class OrderListViewModel(
    private val useCase: PostOrderListUseCase,
    private val postWaiterCallingUseCase: PostWaiterCallingUseCase,
    private val router: OrderListRouter
) : BaseViewModel() {

    private var _state = MutableLiveData<OrderListState>(OrderListState.Initial)
    val state: LiveData<OrderListState> = _state

    fun callWaiter(){
        val thread = Thread {
            try {
                postWaiterCallingUseCase(User.getId())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                        onComplete = {

                        }
                    )
                    .addToDisposableList()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        thread.start()
    }

    fun sendOrder() {
        _state.value = OrderListState.Loading

        val thread = Thread {
            try {
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
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        thread.start()
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