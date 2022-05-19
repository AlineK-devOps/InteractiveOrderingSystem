package ru.nstu.ordsys.features.cook.orderlist

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.nstu.ordsys.features.cook.orderlist.domain.entity.DishForCook
import ru.nstu.ordsys.features.cook.orderlist.ui.CookOrderListFragment
import ru.nstu.ordsys.features.cook.orderlist.ui.details.DishTechnologyScreen

fun getCookOrderListScreen() = FragmentScreen { CookOrderListFragment.newInstance() }

fun getTechnologyScreen(dish: DishForCook) = FragmentScreen { DishTechnologyScreen.newInstance(dish) }