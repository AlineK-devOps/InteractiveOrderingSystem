package ru.nstu.ordsys.features.cook.orderlist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import ru.nstu.ordsys.component.ui.mvvm.BaseViewModel
import ru.nstu.ordsys.features.cook.orderlist.domain.entity.OrderItemForCook
import ru.nstu.ordsys.features.cook.orderlist.domain.entity.OrderItemForUpdate
import ru.nstu.ordsys.features.cook.orderlist.domain.usecase.PostDishStatusUseCase
import ru.nstu.ordsys.order.domain.entity.Order
import ru.nstu.ordsys.order.domain.entity.OrderItemStatus
import ru.nstu.ordsys.shared.user.entity.User

class DishItemViewModel(
    val dish: OrderItemForCook,
    val useCase: PostDishStatusUseCase
) : BaseViewModel() {

    private var _status = MutableLiveData(dish.status)
    val status: LiveData<OrderItemStatus> = _status

    fun startCook(){
        changeDishStatus(OrderItemStatus.IN_COOKING_PROCESS)
        _status.value = OrderItemStatus.IN_COOKING_PROCESS
    }

    fun endCook(){
        changeDishStatus(OrderItemStatus.IN_QUEUE_FOR_SERVING)
    }

    private fun changeDishStatus(status: OrderItemStatus){
        val item = OrderItemForUpdate(
            dish.id,
            User.getId(),
            status
        )

        val thread = Thread {
            try {
                useCase(item)
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