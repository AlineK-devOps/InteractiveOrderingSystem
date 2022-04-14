package ru.nstu.ordsys.shared.dishes.domain.entity

data class Dish(
    val id: Int,
    val category: DishesCategories,
    val name: String,
    val composition: String,
    val price: Int,
    val weight: Int,
    val imageUrl: String
)