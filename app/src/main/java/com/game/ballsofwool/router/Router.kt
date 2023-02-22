package com.game.ballsofwool.router

import android.app.Activity
import androidx.fragment.app.Fragment
import com.game.ballsofwool.MainActivity
import com.game.ballsofwool.R
import com.game.ballsofwool.feature.levels.LevelsFragment
import com.game.ballsofwool.feature.menu.MenuFragment

val Activity.router: Router
    get() = Router(this as MainActivity)

val Fragment.router: Router
    get() = Router(requireActivity() as MainActivity)

class Router(
    private val host: MainActivity,
) : BaseRouter(
    containerId = R.id.container,
    fragmentManager = host.supportFragmentManager,
) {

    fun toMenu() {
        if (isPresent(MenuFragment.TAG)) {
            return
        }
        dismissAllDialogs()
        popToRoot()
        replace(
            fragment = MenuFragment.newInstance(),
            tag = MenuFragment.TAG,
            addToBackStack = false,
        )
    }

    fun toLevels() = replace(
        fragment = LevelsFragment.newInstance(),
        tag = LevelsFragment.TAG,
        addToBackStack = true,
    )
}