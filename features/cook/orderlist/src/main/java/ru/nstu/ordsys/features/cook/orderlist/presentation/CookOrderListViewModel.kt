package ru.nstu.ordsys.features.cook.orderlist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import ru.nstu.ordsys.component.ui.mvvm.BaseViewModel
import ru.nstu.ordsys.features.cook.orderlist.domain.usecase.GetBarOrdersUseCase
import ru.nstu.ordsys.features.cook.orderlist.domain.usecase.GetColdWorkshopOrdersUseCase
import ru.nstu.ordsys.features.cook.orderlist.domain.usecase.GetHotWorkshopOrdersUseCase
import ru.nstu.ordsys.features.cook.orderlist.domain.usecase.GetOrdersUseCase
import ru.nstu.ordsys.features.cook.orderlist.presentation.state.CookOrderListState

class CookOrderListViewModel(
    private val getOrdersUseCase: GetOrdersUseCase,
    private val getHotWorkshopOrdersUseCase: GetHotWorkshopOrdersUseCase,
    private val getColdWorkshopOrdersUseCase: GetColdWorkshopOrdersUseCase,
    private val getBarOrdersUseCase: GetBarOrdersUseCase,
    private val router: CookOrderListRouter
) : BaseViewModel() {

    private var _state = MutableLiveData<CookOrderListState>(CookOrderListState.Initial)
    val state: LiveData<CookOrderListState> = _state

    private var selectedTab: String = "ALL"

    fun getOrders() {
        _state.value = CookOrderListState.Loading

        val thread = Thread {
            try {
                getOrdersUseCase()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                        onSuccess = { orders ->
                            if (orders.isEmpty())
                                _state.value = CookOrderListState.Empty
                            else
                                _state.value = CookOrderListState.Content(orders)
                        },
                        onError = {
                            _state.value = CookOrderListState.Error
                        }
                    )
                    .addToDisposableList()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        thread.start()
    }

    fun getHotWorkshopOrders() {
        _state.value = CookOrderListState.Loading

        val thread = Thread {
            try {
                getHotWorkshopOrdersUseCase()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                        onSuccess = { orders ->
                            if (orders.isEmpty())
                                _state.value = CookOrderListState.Empty
                            else
                                _state.value = CookOrderListState.Content(orders)
                        },
                        onError = {
                            _state.value = CookOrderListState.Error
                        }
                    )
                    .addToDisposableList()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        thread.start()
    }

    fun getColdWorkshopOrders() {
        _state.value = CookOrderListState.Loading

        val thread = Thread {
            try {
                getColdWorkshopOrdersUseCase()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                        onSuccess = { orders ->
                            if (orders.isEmpty())
                                _state.value = CookOrderListState.Empty
                            else
                                _state.value = CookOrderListState.Content(orders)
                        },
                        onError = {
                            _state.value = CookOrderListState.Error
                        }
                    )
                    .addToDisposableList()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        thread.start()
    }

    fun getBarOrders() {
        _state.value = CookOrderListState.Loading

        val thread = Thread {
            try {
                getBarOrdersUseCase()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                        onSuccess = { orders ->
                            if (orders.isEmpty())
                                _state.value = CookOrderListState.Empty
                            else
                                _state.value = CookOrderListState.Content(orders)
                        },
                        onError = {
                            _state.value = CookOrderListState.Error
                        }
                    )
                    .addToDisposableList()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        thread.start()
    }

    fun updateTab(tab: String) {
        selectedTab = tab
    }

    fun updateOrders() {
        when (selectedTab) {
            "ALL" -> getOrders()
            "HOT_WORKSHOP" -> getHotWorkshopOrders()
            "COLD_WORKSHOP" -> getColdWorkshopOrders()
            "BAR" -> getBarOrders()
        }
    }

    fun navigateToSettingsScreen() {
        router.navigateToSettingsScreen()
    }
}