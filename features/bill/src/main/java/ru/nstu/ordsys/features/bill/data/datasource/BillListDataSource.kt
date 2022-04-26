package ru.nstu.ordsys.features.bill.data.datasource

import io.reactivex.Single
import ru.nstu.ordsys.features.bill.data.model.BillItemModel

interface BillListDataSource {

    fun getBillList(tableId: Long): Single<List<BillItemModel>>
}