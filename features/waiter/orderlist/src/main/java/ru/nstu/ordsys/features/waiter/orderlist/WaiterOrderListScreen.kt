package ru.nstu.ordsys.features.waiter.orderlist

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.nstu.ordsys.features.waiter.orderlist.ui.WaiterOrderListFragment

fun getWaiterOrderListScreen() = FragmentScreen { WaiterOrderListFragment.newInstance() }