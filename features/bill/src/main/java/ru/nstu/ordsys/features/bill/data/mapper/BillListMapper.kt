package ru.nstu.ordsys.features.bill.data.mapper

import ru.nstu.ordsys.features.bill.data.model.BillListModel
import ru.nstu.ordsys.shared.dishes.data.mapper.toEntity
import ru.nstu.ordsys.shared.dishes.domain.entity.Dish

fun BillListModel.toEntity(): HashMap<Dish, Int> {
    val bill = HashMap<Dish, Int>()

    for (orderItem in this.bill)
        bill.put(orderItem.dish.toEntity(), orderItem.count)

    return bill
}