package ru.nstu.ordsys.features.cook.orderlist

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.nstu.ordsys.features.cook.orderlist.ui.CookOrderListFragment

fun getCookOrderListScreen() = FragmentScreen { CookOrderListFragment.newInstance() }