package ru.nstu.ordsys.order.presentation

interface OrderListRouter {

    fun navigateToDishesListScreen(position: Int)

    fun navigateToBillListScreen()
}