package ru.nstu.ordsys.features.cook.orderlist.data.model

import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

data class OrderItemForCookModel(
    @SerializedName("id")
    val id: Long,
    @SerializedName("dish")
    val dish: DishForCookModel,
    @SerializedName("status")
    val status: String,
    @SerializedName("orderTime")
    val orderTime: Timestamp,
    @SerializedName("cook")
    val cook: String?
)