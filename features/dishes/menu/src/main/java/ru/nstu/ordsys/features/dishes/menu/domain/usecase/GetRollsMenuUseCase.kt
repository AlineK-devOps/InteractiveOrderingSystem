package ru.nstu.ordsys.features.dishes.menu.domain.usecase

import io.reactivex.Single
import ru.nstu.ordsys.features.dishes.menu.domain.repository.DishesMenuRepository
import ru.nstu.ordsys.shared.dishes.domain.entity.Dish

class GetRollsMenuUseCase(private val repository: DishesMenuRepository) {

    operator fun invoke(): Single<List<Dish>> =
        repository.getRollsMenu()
}