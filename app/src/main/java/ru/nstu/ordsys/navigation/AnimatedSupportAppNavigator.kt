package ru.nstu.ordsys.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.nstu.ordsys.ui.MainActivity
import ru.nstu.ordsys.component.resources.R

class AnimatedSupportAppNavigator(
    activity: MainActivity,
    containerID: Int
) : AppNavigator(activity, containerID) {

    override fun setupFragmentTransaction(
        screen: FragmentScreen,
        fragmentTransaction: FragmentTransaction,
        currentFragment: Fragment?,
        nextFragment: Fragment
    ) {
        super.setupFragmentTransaction(
            screen,
            fragmentTransaction.setCustomAnimations(
                R.anim.zoom_in,
                R.anim.zoom_out,
                R.anim.pop_zoom_in,
                R.anim.pop_zoom_out
            ),
            currentFragment,
            nextFragment
        )
    }
}