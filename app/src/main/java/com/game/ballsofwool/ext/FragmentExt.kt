package com.game.ballsofwool.ext

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.game.ballsofwool.MainActivity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

inline fun <T> Fragment.collectOnResume(
    source: Flow<T>,
    crossinline consumer: (T) -> Unit
) {
    lifecycleScope.launch {
        lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
            source.collect { value ->
                consumer(value)
            }
        }
    }
}

fun nullableIntArgument() = object : ReadWriteProperty<Fragment, Int?> {
    override fun getValue(thisRef: Fragment, property: KProperty<*>): Int =
        thisRef.requireArguments().getInt(property.name)

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: Int?) =
        thisRef.ensureArguments().putInt(property.name, value ?: (-1))
}

fun boolArgument() = object : ReadWriteProperty<Fragment, Boolean> {
    override fun getValue(thisRef: Fragment, property: KProperty<*>): Boolean =
        thisRef.requireArguments().getBoolean(property.name)

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: Boolean) =
        thisRef.ensureArguments().putBoolean(property.name, value)
}

fun intArgument() = object : ReadWriteProperty<Fragment, Int> {
    override fun getValue(thisRef: Fragment, property: KProperty<*>): Int =
        thisRef.requireArguments().getInt(property.name)

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: Int) =
        thisRef.ensureArguments().putInt(property.name, value)
}

fun Fragment.pressBack() {
    lifecycleScope.launchWhenResumed {
        try {
            activity?.onBackPressedDispatcher?.onBackPressed()
        } catch (e: Throwable) {
            Log.e(e.message, "failed to press back")
        }
    }
}

fun Fragment.clickSound() {
    (requireActivity() as MainActivity).clickSound()
}

fun Fragment.ensureArguments(): Bundle {
    var arguments = this.arguments
    if (arguments == null) {
        arguments = Bundle()
        this.arguments = arguments
    }
    return arguments
}