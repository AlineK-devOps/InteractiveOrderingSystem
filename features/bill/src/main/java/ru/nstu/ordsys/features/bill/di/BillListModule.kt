package ru.nstu.ordsys.features.bill.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.nstu.ordsys.features.bill.data.api.BillListApi
import ru.nstu.ordsys.features.bill.data.datasource.BillListDataSource
import ru.nstu.ordsys.features.bill.data.datasource.BillListDataSourceImpl
import ru.nstu.ordsys.features.bill.data.repository.BillListRepositoryImpl
import ru.nstu.ordsys.features.bill.domain.repository.BillListRepository
import ru.nstu.ordsys.features.bill.domain.usecase.GetBillListUseCase
import ru.nstu.ordsys.features.bill.presentation.BillListViewModel
import ru.nstu.ordsys.mockapiserver.changer.MOCK
import ru.nstu.ordsys.mockapiserver.changer.getRetrofit
import ru.nstu.ordsys.mockapiserver.retrofit.createRetrofitService
import ru.nstu.ordsys.waiter.data.datasource.WaiterCallDataSource
import ru.nstu.ordsys.waiter.data.datasource.WaiterCallDataSourceImpl
import ru.nstu.ordsys.waiter.data.repository.WaiterCallRepositoryImpl
import ru.nstu.ordsys.waiter.domain.repository.WaiterCallRepository
import ru.nstu.ordsys.waiter.domain.usecase.PostWaiterCallingUseCase

val billListModule = module {
    factory { createRetrofitService<BillListApi>(getRetrofit(MOCK)) }

    factory<BillListDataSource> { BillListDataSourceImpl(get()) }

    factory<BillListRepository> { BillListRepositoryImpl(get()) }

    factory { GetBillListUseCase(get()) }
    factory { PostWaiterCallingUseCase(get()) }

    viewModel {
        BillListViewModel(
            useCase = get(),
            postWaiterCallingUseCase = get(),
            router = get()
        )
    }
}