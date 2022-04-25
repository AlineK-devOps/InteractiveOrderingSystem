package ru.nstu.ordsys.order.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.nstu.ordsys.component.ui.mvvm.BaseViewModel
import ru.nstu.ordsys.order.domain.entity.Order
import ru.nstu.ordsys.shared.dishes.domain.entity.Dish

class OrderItemViewModel(val dish: Dish, val countDish: Int) : BaseViewModel() {

    private val _count = MutableLiveData(countDish)
    val count: LiveData<Int> = _count

    fun addDishToOrder() {
        Order.addToOrder(dish)

        val previousCount = _count.value ?: 0
        _count.value = previousCount + 1
    }

    fun removeDishFromOrder(){
        Order.removeFromOrder(dish)

        val previousCount = _count.value ?: 0
        _count.value = previousCount - 1
    }
}