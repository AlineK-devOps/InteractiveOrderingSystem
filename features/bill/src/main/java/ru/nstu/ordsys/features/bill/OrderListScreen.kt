package ru.nstu.ordsys.features.bill

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.nstu.ordsys.order.ui.OrderListFragment

fun getOrderListScreen() = FragmentScreen { OrderListFragment.newInstance() }