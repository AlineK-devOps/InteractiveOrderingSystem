package ru.nstu.ordsys.features.bill.domain.entity

import ru.nstu.ordsys.shared.dishes.domain.entity.Dish

data class BillItem(
    val dish: Dish,
    val count: Int
)