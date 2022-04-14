package ru.nstu.ordsys.features.dishes.menu.data.mapper

import ru.nstu.ordsys.shared.dishes.data.mapper.toEntity
import ru.nstu.ordsys.shared.dishes.data.model.DishModel
import ru.nstu.ordsys.shared.dishes.domain.entity.Dish

fun List<DishModel>.toEntity(): List<Dish> =
    map { it.toEntity() }