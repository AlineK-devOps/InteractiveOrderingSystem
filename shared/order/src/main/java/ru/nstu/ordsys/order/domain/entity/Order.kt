package ru.nstu.ordsys.order.domain.entity

import ru.nstu.ordsys.shared.dishes.domain.entity.Dish

object Order {

    private val order = HashMap<Dish, Int>()

    fun addToOrder(dish: Dish) {
        if (order.containsKey(dish)) {
            val previousCount = order.getOrDefault(dish, 0)
            order.put(key = dish, value = previousCount + 1)
        } else
            order.put(key = dish, value = 1)
    }

    fun removeFromOrder(dish: Dish) {
        if (order.containsKey(dish)) {
            val previousCount = order.getOrDefault(dish, 0)
            if (previousCount > 1)
                order.put(key = dish, value = previousCount - 1)
            else
                order.remove(dish)
        }
    }

    fun getDishCount(dish: Dish): Int =
        order.getOrDefault(dish, 0)

    fun getTotalPrice(): Int =
        order.map { (dish, count) -> dish.price * count }.sum()

    fun clearOrder() {
        order.clear()
    }
}