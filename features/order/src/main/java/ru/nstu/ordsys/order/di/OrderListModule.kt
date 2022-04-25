package ru.nstu.ordsys.order.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.nstu.ordsys.order.presentation.OrderListViewModel

val orderListModule = module {
    viewModel {
        OrderListViewModel(
            router = get()
        )
    }
}