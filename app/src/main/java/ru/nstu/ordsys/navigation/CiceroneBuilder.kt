package ru.nstu.ordsys.navigation

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router

fun buildCicerone(): Cicerone<Router> =
    Cicerone.create().apply {
        //router.newRootScreen(StartApplicationScreen.getScreen())
    }