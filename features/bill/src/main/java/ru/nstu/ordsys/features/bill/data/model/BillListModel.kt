package ru.nstu.ordsys.features.bill.data.model

import com.google.gson.annotations.SerializedName

data class BillListModel(
    @SerializedName("bill")
    val bill: List<BillItemModel>
)