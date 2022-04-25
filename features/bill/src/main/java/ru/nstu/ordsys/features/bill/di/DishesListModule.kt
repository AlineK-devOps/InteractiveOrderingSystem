package ru.nstu.ordsys.features.dishes.menu.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.nstu.ordsys.features.bill.data.api.BillListApi
import ru.nstu.ordsys.features.bill.data.datasource.DishesListDatasource
import ru.nstu.ordsys.features.bill.data.datasource.BillListDataSourceImpl
import ru.nstu.ordsys.features.bill.data.repository.BillListRepositoryImpl
import ru.nstu.ordsys.features.bill.domain.repository.BillListRepository
import ru.nstu.ordsys.features.bill.domain.usecase.GetBillListUseCase
import ru.nstu.ordsys.features.dishes.menu.domain.usecase.*
import ru.nstu.ordsys.features.dishes.menu.presentation.DishesListViewModel
import ru.nstu.ordsys.mockapiserver.changer.MOCK
import ru.nstu.ordsys.mockapiserver.changer.getRetrofit
import ru.nstu.ordsys.mockapiserver.retrofit.createRetrofitService

val dishesListModule = module {
    factory { createRetrofitService<BillListApi>(getRetrofit(MOCK)) }

    factory<DishesListDatasource> { BillListDataSourceImpl(get()) }

    factory<BillListRepository> { BillListRepositoryImpl(get()) }

    factory { GetBillListUseCase(get()) }
    factory { GetDrinksMenuUseCase(get()) }
    factory { GetHotRollsMenuUseCase(get()) }
    factory { GetRollsMenuUseCase(get()) }
    factory { GetSnacksMenuUseCase(get()) }
    factory { GetSoupsMenuUseCase(get()) }
    factory { GetSushiMenuUseCase(get()) }
    factory { GetWokMenuUseCase(get()) }

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
            router = get()
        )
    }
}