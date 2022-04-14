package ru.nstu.ordsys.ui

import com.github.terrakok.cicerone.NavigatorHolder
import org.koin.android.ext.android.inject
import ru.nstu.ordsys.R
import ru.nstu.ordsys.navigation.AnimatedSupportAppNavigator

class MainActivity : BaseActivity() {

    override val layout: Int = R.layout.main_activity

    private val navigatorHolder: NavigatorHolder by inject()
    private val navigator = AnimatedSupportAppNavigator(this, R.id.container)

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }
}
