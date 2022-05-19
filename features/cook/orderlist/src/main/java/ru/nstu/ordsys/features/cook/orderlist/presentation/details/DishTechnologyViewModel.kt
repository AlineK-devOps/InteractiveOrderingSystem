package ru.nstu.ordsys.features.cook.orderlist.presentation.details

import ru.nstu.ordsys.component.ui.mvvm.BaseViewModel

class DishTechnologyViewModel(
    val router: DishTechnologyRouter
) : BaseViewModel() {

    fun exit() {
        router.exit()
    }
}