package ru.nstu.ordsys.order

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.nstu.ordsys.order.ui.OrderListFragment

fun getOrderListScreen() = FragmentScreen { OrderListFragment.newInstance() }