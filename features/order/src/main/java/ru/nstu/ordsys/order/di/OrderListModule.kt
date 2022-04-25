package ru.nstu.ordsys.order.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.nstu.ordsys.mockapiserver.changer.MOCK
import ru.nstu.ordsys.mockapiserver.changer.getRetrofit
import ru.nstu.ordsys.mockapiserver.retrofit.createRetrofitService
import ru.nstu.ordsys.order.data.api.OrderListApi
import ru.nstu.ordsys.order.data.datasource.OrderListDataSource
import ru.nstu.ordsys.order.data.datasource.OrderListDataSourceImpl
import ru.nstu.ordsys.order.data.repository.OrderListRepositoryImpl
import ru.nstu.ordsys.order.domain.repository.OrderListRepository
import ru.nstu.ordsys.order.domain.usecase.PostOrderListUseCase
import ru.nstu.ordsys.order.presentation.OrderListViewModel

val orderListModule = module {
    factory { createRetrofitService<OrderListApi>(getRetrofit(MOCK)) }
    factory<OrderListDataSource> { OrderListDataSourceImpl(get()) }
    factory<OrderListRepository> { OrderListRepositoryImpl(get()) }
    factory { PostOrderListUseCase(get()) }

    viewModel {
        OrderListViewModel(
            useCase = get(),
            router = get()
        )
    }
}