package ru.nstu.ordsys.features.bill.domain.usecase

import io.reactivex.Single
import ru.nstu.ordsys.features.bill.domain.entity.BillItem
import ru.nstu.ordsys.features.bill.domain.repository.BillListRepository
import ru.nstu.ordsys.shared.dishes.domain.entity.Dish

class GetBillListUseCase(private val repository: BillListRepository) {

    operator fun invoke(tableId: Long): Single<List<BillItem>> =
        repository.getBillList(tableId)
}