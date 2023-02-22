package com.game.ballsofwool.router

import androidx.annotation.IdRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit

abstract class BaseRouter(
    @IdRes private val containerId: Int,
    private val fragmentManager: FragmentManager,
) {

    fun isEmpty(): Boolean =
        fragmentManager.findFragmentById(containerId) == null

    fun pop(): Boolean {
        if (fragmentManager.backStackEntryCount > 0) {
            fragmentManager.popBackStack()
            return true
        }
        return false
    }

    fun popToRoot() =
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

    fun getFragmentByTag(tag: String) =
        fragmentManager.findFragmentByTag(tag)

    fun isPresent(tag: String): Boolean {
        return fragmentManager.findFragmentByTag(tag) != null
    }

    protected fun dismissAllDialogs() {
        fragmentManager.fragments.forEach { fragment ->
            (fragment as? DialogFragment)?.dismiss()
        }
    }

    protected fun replace(
        fragment: Fragment,
        tag: String,
        addToBackStack: Boolean = true,
    ) = fragmentManager.commit {
        replace(containerId, fragment, tag)
        if (addToBackStack) {
            addToBackStack(tag)
        }
    }
}