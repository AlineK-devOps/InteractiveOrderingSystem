package ru.nstu.ordsys.features.bill.presentation

interface BillListRouter {

    fun navigateToOrderListScreen()

    fun navigateToDishesListScreen(position: Int)
}