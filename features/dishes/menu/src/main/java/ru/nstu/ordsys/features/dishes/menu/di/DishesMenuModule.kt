package ru.nstu.ordsys.features.dishes.menu.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.nstu.ordsys.features.dishes.menu.data.api.DishesMenuApi
import ru.nstu.ordsys.features.dishes.menu.data.datasource.DishesMenuDatasource
import ru.nstu.ordsys.features.dishes.menu.data.datasource.DishesMenuDatasourceImpl
import ru.nstu.ordsys.features.dishes.menu.data.repository.DishesMenuRepositoryImpl
import ru.nstu.ordsys.features.dishes.menu.domain.repository.DishesMenuRepository
import ru.nstu.ordsys.features.dishes.menu.domain.usecase.*
import ru.nstu.ordsys.features.dishes.menu.presentation.DishesMenuViewModel
import ru.nstu.ordsys.mockapiserver.changer.MOCK
import ru.nstu.ordsys.mockapiserver.changer.getRetrofit
import ru.nstu.ordsys.mockapiserver.retrofit.createRetrofitService

val dishesMenuModule = module {
    factory { createRetrofitService<DishesMenuApi>(getRetrofit(MOCK)) }

    factory<DishesMenuDatasource> { DishesMenuDatasourceImpl(get()) }

    factory<DishesMenuRepository> { DishesMenuRepositoryImpl(get()) }

    factory { GetAdditionallyMenuUseCase(get()) }
    factory { GetDrinksMenuUseCase(get()) }
    factory { GetHotRollsMenuUseCase(get()) }
    factory { GetRollsMenuUseCase(get()) }
    factory { GetSnacksMenuUseCase(get()) }
    factory { GetSoupsMenuUseCase(get()) }
    factory { GetSushiMenuUseCase(get()) }
    factory { GetWokMenuUseCase(get()) }

    viewModel {
        DishesMenuViewModel(
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