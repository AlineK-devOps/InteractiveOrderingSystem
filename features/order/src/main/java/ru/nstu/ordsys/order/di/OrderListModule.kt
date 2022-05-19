package ru.nstu.ordsys.order.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.nstu.ordsys.mockapiserver.changer.MOCK
import ru.nstu.ordsys.mockapiserver.changer.ORIGINAL
import ru.nstu.ordsys.mockapiserver.changer.getRetrofit
import ru.nstu.ordsys.mockapiserver.retrofit.createRetrofitService
import ru.nstu.ordsys.order.data.api.OrderListApi
import ru.nstu.ordsys.order.data.datasource.OrderListDataSource
import ru.nstu.ordsys.order.data.datasource.OrderListDataSourceImpl
import ru.nstu.ordsys.order.data.repository.OrderListRepositoryImpl
import ru.nstu.ordsys.order.domain.repository.OrderListRepository
import ru.nstu.ordsys.order.domain.usecase.PostOrderListUseCase
import ru.nstu.ordsys.order.presentation.OrderListViewModel
import ru.nstu.ordsys.waiter.data.datasource.WaiterCallDataSource
import ru.nstu.ordsys.waiter.data.datasource.WaiterCallDataSourceImpl
import ru.nstu.ordsys.waiter.data.repository.WaiterCallRepositoryImpl
import ru.nstu.ordsys.waiter.domain.repository.WaiterCallRepository
import ru.nstu.ordsys.waiter.domain.usecase.PostWaiterCallingUseCase

val orderListModule = module {
    factory { createRetrofitService<OrderListApi>(getRetrofit(ORIGINAL)) }
    factory<OrderListDataSource> { OrderListDataSourceImpl(get()) }
    factory<OrderListRepository> { OrderListRepositoryImpl(get()) }
    factory { PostOrderListUseCase(get()) }
    factory { PostWaiterCallingUseCase(get()) }

    viewModel {
        OrderListViewModel(
            useCase = get(),
            postWaiterCallingUseCase = get(),
            router = get()
        )
    }
}