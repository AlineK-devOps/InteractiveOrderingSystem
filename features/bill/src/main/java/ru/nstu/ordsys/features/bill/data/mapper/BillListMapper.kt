package ru.nstu.ordsys.features.bill.data.mapper

import ru.nstu.ordsys.features.bill.data.model.BillListModel
import ru.nstu.ordsys.features.bill.domain.entity.BillItem
import ru.nstu.ordsys.shared.dishes.data.mapper.toEntity

fun BillListModel.toEntity(): List<BillItem> =
    this.bill.map { itemModel ->
        BillItem(
            itemModel.dish.toEntity(),
            itemModel.count
        )
    }