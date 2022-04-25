package ru.nstu.ordsys.features.bill.data.datasource

import io.reactivex.Single
import ru.nstu.ordsys.features.bill.data.api.BillListApi
import ru.nstu.ordsys.features.bill.data.model.BillListModel

class BillListDataSourceImpl(private val api: BillListApi) : BillListDataSource {

    override fun getBillList(tableId: Long): Single<BillListModel> =
        api.getBillList(tableId)
}