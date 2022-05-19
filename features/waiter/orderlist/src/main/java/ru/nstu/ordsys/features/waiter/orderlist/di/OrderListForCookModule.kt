package ru.nstu.ordsys.features.waiter.orderlist.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.nstu.ordsys.features.waiter.orderlist.data.api.WaiterOrderListApi
import ru.nstu.ordsys.features.waiter.orderlist.data.datasource.WaiterOrderListDataSource
import ru.nstu.ordsys.features.waiter.orderlist.data.datasource.WaiterOrderListDataSourceImpl
import ru.nstu.ordsys.features.waiter.orderlist.data.repository.WaiterOrderListRepositoryImpl
import ru.nstu.ordsys.features.waiter.orderlist.domain.repository.WaiterOrderListRepository
import ru.nstu.ordsys.features.waiter.orderlist.domain.usecase.*
import ru.nstu.ordsys.features.waiter.orderlist.presentation.WaiterOrderListViewModel
import ru.nstu.ordsys.mockapiserver.changer.ORIGINAL
import ru.nstu.ordsys.mockapiserver.changer.getRetrofit
import ru.nstu.ordsys.mockapiserver.retrofit.createRetrofitService

val waiterOrderListModule = module {
    factory { createRetrofitService<WaiterOrderListApi>(getRetrofit(ORIGINAL)) }

    factory<WaiterOrderListDataSource> { WaiterOrderListDataSourceImpl(get()) }

    factory<WaiterOrderListRepository> { WaiterOrderListRepositoryImpl(get()) }

    factory { GetOrdersUseCase(get()) }
    factory { PostDishStatusUseCase(get()) }
    factory { DoneWaiterCallingUseCase(get()) }
    factory { DeleteDishUseCase(get()) }
    factory { DeleteOrderUseCase(get()) }
    factory { DoneOrderUseCase(get()) }

    viewModel {
        WaiterOrderListViewModel(
            getOrdersUseCase = get(),
            router = get()
        )
    }
}