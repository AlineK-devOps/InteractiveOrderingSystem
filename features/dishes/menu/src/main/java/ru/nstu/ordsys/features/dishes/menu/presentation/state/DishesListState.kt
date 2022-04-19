package ru.nstu.ordsys.features.dishes.menu.presentation.state

import ru.nstu.ordsys.shared.dishes.domain.entity.Dish

sealed class DishesListState {

    object Initial : DishesListState()

    object Loading : DishesListState()

    data class Content(
        val dishesMenu: List<Dish>
    ) : DishesListState()

    object Error : DishesListState()
}