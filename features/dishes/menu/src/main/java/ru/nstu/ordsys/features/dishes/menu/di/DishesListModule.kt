package ru.nstu.ordsys.features.dishes.menu.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.nstu.ordsys.features.dishes.menu.data.api.DishesListApi
import ru.nstu.ordsys.features.dishes.menu.data.datasource.DishesListDatasource
import ru.nstu.ordsys.features.dishes.menu.data.datasource.DishesListDatasourceImpl
import ru.nstu.ordsys.features.dishes.menu.data.repository.DishesListRepositoryImpl
import ru.nstu.ordsys.features.dishes.menu.domain.repository.DishesListRepository
import ru.nstu.ordsys.features.dishes.menu.domain.usecase.*
import ru.nstu.ordsys.features.dishes.menu.presentation.DishesListViewModel
import ru.nstu.ordsys.mockapiserver.changer.MOCK
import ru.nstu.ordsys.mockapiserver.changer.ORIGINAL
import ru.nstu.ordsys.mockapiserver.changer.getRetrofit
import ru.nstu.ordsys.mockapiserver.retrofit.createRetrofitService
import ru.nstu.ordsys.waiter.data.datasource.WaiterCallDataSource
import ru.nstu.ordsys.waiter.data.datasource.WaiterCallDataSourceImpl
import ru.nstu.ordsys.waiter.data.repository.WaiterCallRepositoryImpl
import ru.nstu.ordsys.waiter.domain.repository.WaiterCallRepository
import ru.nstu.ordsys.waiter.domain.usecase.PostWaiterCallingUseCase

val dishesListModule = module {
    factory { createRetrofitService<DishesListApi>(getRetrofit(ORIGINAL)) }

    factory<DishesListDatasource> { DishesListDatasourceImpl(get()) }

    factory<DishesListRepository> { DishesListRepositoryImpl(get()) }

    factory { GetAdditionallyMenuUseCase(get()) }
    factory { GetDrinksMenuUseCase(get()) }
    factory { GetHotRollsMenuUseCase(get()) }
    factory { GetRollsMenuUseCase(get()) }
    factory { GetSnacksMenuUseCase(get()) }
    factory { GetSoupsMenuUseCase(get()) }
    factory { GetSushiMenuUseCase(get()) }
    factory { GetWokMenuUseCase(get()) }
    factory { PostWaiterCallingUseCase(get()) }

    viewModel {
        DishesListViewModel(
            getAdditionallyMenuUseCase = get(),
            getDrinksMenuUseCase = get(),
            getHotRollsMenuUseCase = get(),
            getRollsMenuUseCase = get(),
            getSnacksMenuUseCase = get(),
            getSoupsMenuUseCase = get(),
            getSushiMenuUseCase = get(),
            getWokMenuUseCase = get(),
            postWaiterCallingUseCase = get(),
            router = get()
        )
    }
}