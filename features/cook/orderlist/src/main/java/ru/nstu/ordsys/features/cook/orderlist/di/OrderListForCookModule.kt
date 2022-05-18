package ru.nstu.ordsys.features.cook.orderlist.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.nstu.ordsys.features.cook.orderlist.data.api.CookOrderListApi
import ru.nstu.ordsys.features.cook.orderlist.data.datasource.CookOrderListDataSource
import ru.nstu.ordsys.features.cook.orderlist.data.datasource.CookOrderListDataSourceImpl
import ru.nstu.ordsys.features.cook.orderlist.data.repository.CookOrderListRepositoryImpl
import ru.nstu.ordsys.features.cook.orderlist.domain.repository.CookOrderListRepository
import ru.nstu.ordsys.features.cook.orderlist.domain.usecase.GetBarOrdersUseCase
import ru.nstu.ordsys.features.cook.orderlist.domain.usecase.GetColdWorkshopOrdersUseCase
import ru.nstu.ordsys.features.cook.orderlist.domain.usecase.GetHotWorkshopOrdersUseCase
import ru.nstu.ordsys.features.cook.orderlist.domain.usecase.GetOrdersUseCase
import ru.nstu.ordsys.features.cook.orderlist.presentation.CookOrderListViewModel
import ru.nstu.ordsys.mockapiserver.changer.ORIGINAL
import ru.nstu.ordsys.mockapiserver.changer.getRetrofit
import ru.nstu.ordsys.mockapiserver.retrofit.createRetrofitService

val cookOrderListModule = module {
    factory { createRetrofitService<CookOrderListApi>(getRetrofit(ORIGINAL)) }

    factory<CookOrderListDataSource> { CookOrderListDataSourceImpl(get()) }

    factory<CookOrderListRepository> { CookOrderListRepositoryImpl(get()) }

    factory { GetOrdersUseCase(get()) }
    factory { GetHotWorkshopOrdersUseCase(get()) }
    factory { GetColdWorkshopOrdersUseCase(get()) }
    factory { GetBarOrdersUseCase(get()) }

    viewModel {
        CookOrderListViewModel(
            getOrdersUseCase = get(),
            getColdWorkshopOrdersUseCase = get(),
            getHotWorkshopOrdersUseCase = get(),
            getBarOrdersUseCase = get(),
            router = get()
        )
    }
}