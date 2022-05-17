package ru.nstu.ordsys.features.cook.orderlist

import com.github.terrakok.cicerone.androidx.FragmentScreen

fun getCookOrderListScreen() = FragmentScreen { CookOrderListFragment.newInstance() }