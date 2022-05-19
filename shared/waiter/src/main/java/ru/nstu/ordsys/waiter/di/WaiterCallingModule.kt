package ru.nstu.ordsys.waiter.di

import org.koin.dsl.module
import ru.nstu.ordsys.mockapiserver.changer.MOCK
import ru.nstu.ordsys.mockapiserver.changer.getRetrofit
import ru.nstu.ordsys.mockapiserver.retrofit.createRetrofitService
import ru.nstu.ordsys.waiter.data.api.WaiterCallApi
import ru.nstu.ordsys.waiter.data.datasource.WaiterCallDataSource
import ru.nstu.ordsys.waiter.data.datasource.WaiterCallDataSourceImpl
import ru.nstu.ordsys.waiter.data.repository.WaiterCallRepositoryImpl
import ru.nstu.ordsys.waiter.domain.repository.WaiterCallRepository

val waiterCallingModule = module {
    factory { createRetrofitService<WaiterCallApi>(getRetrofit(MOCK)) }

    factory<WaiterCallDataSource> { WaiterCallDataSourceImpl(get()) }

    factory<WaiterCallRepository> { WaiterCallRepositoryImpl(get()) }
}