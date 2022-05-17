package ru.nstu.ordsys.features.cook.orderlist.domain.entity

data class DishForCook(
    val name: String,
    val weight: Int,
    val cookingTime: Int,
    val imageUrl: String,
    val recipe: String?
)