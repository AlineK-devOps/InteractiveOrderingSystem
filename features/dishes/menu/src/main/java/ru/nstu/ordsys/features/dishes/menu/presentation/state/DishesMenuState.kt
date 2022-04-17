package ru.nstu.ordsys.features.dishes.menu.presentation.state

import ru.nstu.ordsys.shared.dishes.domain.entity.Dish

sealed class DishesMenuState {

    object Initial : DishesMenuState()

    object Loading : DishesMenuState()

    data class Content(
        val dishesMenu: List<Dish>
    ) : DishesMenuState()

    object Error : DishesMenuState()
}