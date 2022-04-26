package ru.nstu.ordsys.features.bill.data.mapper

import ru.nstu.ordsys.features.bill.data.model.BillItemModel
import ru.nstu.ordsys.features.bill.domain.entity.BillItem
import ru.nstu.ordsys.shared.dishes.data.mapper.toEntity

fun List<BillItemModel>.toEntity(): List<BillItem> =
    this.map { itemModel ->
        BillItem(
            itemModel.dish.toEntity(),
            itemModel.count
        )
    }