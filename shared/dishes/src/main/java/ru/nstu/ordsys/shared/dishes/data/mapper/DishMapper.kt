package ru.nstu.ordsys.shared.dishes.data.mapper

import ru.nstu.ordsys.shared.dishes.data.model.DishModel
import ru.nstu.ordsys.shared.dishes.domain.entity.Dish

fun DishModel.toEntity(): Dish =
    Dish(
        id = id,
        category = category,
        name = name,
        composition = composition,
        price = price,
        weight = weight,
        imageUrl = imageUrl
    )