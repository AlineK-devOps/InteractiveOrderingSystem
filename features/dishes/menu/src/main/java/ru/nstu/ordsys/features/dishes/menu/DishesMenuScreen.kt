package ru.nstu.ordsys.features.dishes.menu

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.nstu.ordsys.features.dishes.menu.ui.DishesMenuFragment

fun getDishesMenuScreen() = FragmentScreen { DishesMenuFragment.newInstance() }