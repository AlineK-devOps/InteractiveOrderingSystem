package ru.nstu.ordsys.features.bill.presentation.state

import ru.nstu.ordsys.features.bill.domain.entity.BillItem

sealed class BillListState {

    object Initial : BillListState()

    object Loading : BillListState()

    object Empty : BillListState()

    data class Content(
        val bill: List<BillItem>
    ) : BillListState()

    object Error : BillListState()
}