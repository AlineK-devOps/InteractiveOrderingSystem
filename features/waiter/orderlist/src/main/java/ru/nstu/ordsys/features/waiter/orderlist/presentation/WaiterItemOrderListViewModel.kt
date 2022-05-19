package ru.nstu.ordsys.features.waiter.orderlist.presentation

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import ru.nstu.ordsys.component.ui.mvvm.BaseViewModel
import ru.nstu.ordsys.features.waiter.orderlist.domain.entity.OrderListForWaiter
import ru.nstu.ordsys.features.waiter.orderlist.domain.usecase.DeleteOrderUseCase
import ru.nstu.ordsys.features.waiter.orderlist.domain.usecase.DoneOrderUseCase
import ru.nstu.ordsys.features.waiter.orderlist.domain.usecase.DoneWaiterCallingUseCase

class WaiterItemOrderListViewModel(
    val itemOrder: OrderListForWaiter,
    private val doneWaiterCallingUseCase: DoneWaiterCallingUseCase,
    private val deleteOrderUseCase: DeleteOrderUseCase,
    private val doneOrderUseCase: DoneOrderUseCase
) : BaseViewModel() {

    fun deleteOrder(){
        val thread = Thread {
            try {
                deleteOrderUseCase(itemOrder.tableId)
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

    fun doneOrder(){
        val thread = Thread {
            try {
                doneOrderUseCase(itemOrder.tableId)
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

    fun doneWaiterCalling(){
        val thread = Thread {
            try {
                doneWaiterCallingUseCase(itemOrder.tableId)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                        onComplete = {
                            itemOrder.waiterCalled = false
                        }
                    )
                    .addToDisposableList()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        thread.start()
    }
}