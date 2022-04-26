package ru.nstu.ordsys.features.bill

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.nstu.ordsys.features.bill.ui.BillListFragment

fun getBillListScreen() = FragmentScreen { BillListFragment.newInstance() }