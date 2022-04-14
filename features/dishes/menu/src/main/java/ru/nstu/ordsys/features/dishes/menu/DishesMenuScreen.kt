package ru.nstu.ordsys.features.dishes.menu

import com.github.terrakok.cicerone.androidx.FragmentScreen

fun getDishesMenuScreen() = FragmentScreen { DishesMenuFragment.newInstance() }