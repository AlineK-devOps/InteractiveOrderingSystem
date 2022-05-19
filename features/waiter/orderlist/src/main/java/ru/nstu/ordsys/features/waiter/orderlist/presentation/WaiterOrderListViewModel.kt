package ru.nstu.ordsys.features.waiter.orderlist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import ru.nstu.ordsys.component.ui.mvvm.BaseViewModel
import ru.nstu.ordsys.features.waiter.orderlist.domain.usecase.GetOrdersUseCase
import ru.nstu.ordsys.features.waiter.orderlist.presentation.state.WaiterOrderListState

class WaiterOrderListViewModel(
    private val getOrdersUseCase: GetOrdersUseCase,
    private val router: WaiterOrderListRouter
) : BaseViewModel() {

    private var _state = MutableLiveData<WaiterOrderListState>(WaiterOrderListState.Initial)
    val state: LiveData<WaiterOrderListState> = _state

    fun getOrders() {
        _state.value = WaiterOrderListState.Loading

        val thread = Thread {
            try {
                getOrdersUseCase()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                        onSuccess = { orders ->
                            if (orders.isEmpty())
                                _state.value = WaiterOrderListState.Empty
                            else
                                _state.value = WaiterOrderListState.Content(orders)
                        },
                        onError = {
                            _state.value = WaiterOrderListState.Error
                        }
                    )
                    .addToDisposableList()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        thread.start()
    }

    fun navigateToSettingsScreen() {
        router.navigateToSettingsScreen()
    }
}