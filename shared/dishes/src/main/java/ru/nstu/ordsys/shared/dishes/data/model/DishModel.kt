package ru.nstu.ordsys.shared.dishes.data.model

import com.google.gson.annotations.SerializedName
import ru.nstu.ordsys.shared.dishes.domain.entity.DishesCategories

data class DishModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("category")
    val category: DishesCategories,
    @SerializedName("name")
    val name: String,
    @SerializedName("composition")
    val composition: String,
    @SerializedName("price")
    val price: Int,
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("imageUrl")
    val imageUrl: String
)