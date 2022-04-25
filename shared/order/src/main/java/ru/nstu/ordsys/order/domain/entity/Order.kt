package ru.nstu.ordsys.order.domain.entity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.nstu.ordsys.shared.dishes.domain.entity.Dish

object Order {

    val order = HashMap<Dish, Int>()
    val orderLiveData = MutableLiveData(order)

    private val total = MutableLiveData(0)
    val totalPrice: LiveData<Int> = total

    fun addToOrder(dish: Dish) {
        if (order.containsKey(dish)) {
            val previousCount = order.getOrDefault(dish, 0)
            order.put(key = dish, value = previousCount + 1)
        } else
            order.put(key = dish, value = 1)

        updateOrderList()
        updateTotalPrice()
    }

    fun removeFromOrder(dish: Dish) {
        if (order.containsKey(dish)) {
            val previousCount = order.getOrDefault(dish, 0)
            if (previousCount > 1)
                order.put(key = dish, value = previousCount - 1)
            else
                order.remove(dish)
        }

        updateOrderList()
        updateTotalPrice()
    }

    private fun updateOrderList(){
        orderLiveData.value = order
    }

    fun getDishCount(dish: Dish): Int =
        order.getOrDefault(dish, 0)

    fun clearOrder() {
        order.clear()

        updateOrderList()
        updateTotalPrice()
    }

    private fun updateTotalPrice(){
        total.value = order.map { (dish, count) -> dish.price * count }.sum()
    }
}