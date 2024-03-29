package ru.nstu.ordsys.features.bill.domain.repository

import io.reactivex.Single
import ru.nstu.ordsys.features.bill.domain.entity.BillItem
import ru.nstu.ordsys.shared.dishes.domain.entity.Dish

interface BillListRepository {

    fun getBillList(tableId: Long): Single<List<BillItem>>
}