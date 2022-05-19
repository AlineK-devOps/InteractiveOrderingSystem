package ru.nstu.ordsys.features.waiter.orderlist.data.model

import com.google.gson.annotations.SerializedName
import java.sql.Timestamp

data class OrderItemForWaiterModel(
    @SerializedName("id")
    val id: Long,
    @SerializedName("dish")
    val dish: DishForWaiterModel,
    @SerializedName("status")
    val status: String,
    @SerializedName("orderTime")
    val orderTime: Timestamp,
    @SerializedName("cook")
    val cook: String?,
    @SerializedName("waiter")
    val waiter: String?
)