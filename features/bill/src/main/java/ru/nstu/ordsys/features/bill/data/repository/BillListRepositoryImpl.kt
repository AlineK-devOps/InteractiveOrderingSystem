package ru.nstu.ordsys.features.bill.data.repository

import io.reactivex.Single
import ru.nstu.ordsys.features.bill.data.datasource.BillListDataSource
import ru.nstu.ordsys.features.bill.data.mapper.toEntity
import ru.nstu.ordsys.features.bill.data.model.BillListModel
import ru.nstu.ordsys.features.bill.domain.entity.BillItem
import ru.nstu.ordsys.features.bill.domain.repository.BillListRepository

class BillListRepositoryImpl(private val datasource: BillListDataSource) : BillListRepository {

    override fun getBillList(tableId: Long): Single<List<BillItem>> =
        datasource.getBillList(tableId).map(BillListModel::toEntity)
}
