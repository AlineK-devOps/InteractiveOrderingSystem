package ru.nstu.ordsys.features.dishes.menu.domain.usecase

import io.reactivex.Single
import ru.nstu.ordsys.features.dishes.menu.domain.repository.DishesListRepository
import ru.nstu.ordsys.shared.dishes.domain.entity.Dish

class GetSoupsMenuUseCase(private val repository: DishesListRepository) {

    operator fun invoke(): Single<List<Dish>> =
        repository.getSoupsMenu()
}