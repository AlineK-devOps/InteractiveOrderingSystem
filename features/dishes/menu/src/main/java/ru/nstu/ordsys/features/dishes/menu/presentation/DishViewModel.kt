package ru.nstu.ordsys.features.dishes.menu.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.nstu.ordsys.component.ui.mvvm.BaseViewModel
import ru.nstu.ordsys.order.domain.entity.Order
import ru.nstu.ordsys.shared.dishes.domain.entity.Dish

class DishViewModel(val dish: Dish) : BaseViewModel() {

    private val _count = MutableLiveData(Order.getDishCount(dish))
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