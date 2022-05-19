package ru.nstu.ordsys.features.waiter.orderlist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import ru.nstu.ordsys.component.ui.mvvm.BaseViewModel
import ru.nstu.ordsys.features.waiter.orderlist.domain.entity.OrderItemForUpdate
import ru.nstu.ordsys.features.waiter.orderlist.domain.entity.OrderItemForWaiter
import ru.nstu.ordsys.features.waiter.orderlist.domain.usecase.DeleteDishUseCase
import ru.nstu.ordsys.features.waiter.orderlist.domain.usecase.PostDishStatusUseCase
import ru.nstu.ordsys.order.domain.entity.OrderItemStatus
import ru.nstu.ordsys.shared.user.entity.User

class DishItemViewModel(
    val dish: OrderItemForWaiter,
    val postDishStatusUseCase: PostDishStatusUseCase,
    val deleteDishUseCase: DeleteDishUseCase
) : BaseViewModel() {

    private var _status = MutableLiveData(dish.status)
    val status: LiveData<OrderItemStatus> = _status

    fun servingDish() {
        changeDishStatus(OrderItemStatus.SERVED)

        dish.status = OrderItemStatus.SERVED
        dish.waiter = User.getName()

        _status.value = OrderItemStatus.SERVED
    }

    fun deleteDish(){
        val thread = Thread {
            try {
                deleteDishUseCase(dish.id)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                        onComplete = {
                            //_state.value = OrderListState.SendOperationSuccess
                        }
                    )
                    .addToDisposableList()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        thread.start()
    }

    private fun changeDishStatus(status: OrderItemStatus) {
        val item = OrderItemForUpdate(
            dish.id,
            User.getId(),
            status
        )

        val thread = Thread {
            try {
                postDishStatusUseCase(item)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(
                        onComplete = {
                            //_state.value = OrderListState.SendOperationSuccess
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