package ru.nstu.ordsys.features.bill.data.model

import com.google.gson.annotations.SerializedName
import ru.nstu.ordsys.shared.dishes.data.model.DishModel

data class BillItemModel(
    @SerializedName("dish")
    val dish: DishModel,
    @SerializedName("count")
    val count: Int
)