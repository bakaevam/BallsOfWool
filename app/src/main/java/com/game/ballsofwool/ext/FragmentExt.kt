package com.game.ballsofwool.ext

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.game.ballsofwool.MainActivity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.io.Serializable
import kotlin.math.roundToInt
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

inline fun <T> Fragment.collect(
    source: Flow<T>,
    crossinline consumer: (T) -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launchWhenResumed {
        source.collect {
            consumer(it)
        }
    }
}

fun DialogFragment.showIfNotShowing(fragmentManager: FragmentManager, tag: String) {
    if (fragmentManager.findFragmentByTag(tag) == null) {
        show(fragmentManager, tag)
    }
}

fun Fragment.showToast(text: String) {
    Toast.makeText(requireContext(), text, Toast.LENGTH_LONG).show()
}

fun Fragment.showToast(@StringRes resId: Int) {
    showToast(getString(resId))
}

fun FragmentManager.clearBackStack() {
    for (i in 0 until backStackEntryCount) {
        popBackStack()
    }
}

fun Context.pxToDp(px: Int) = (px / resources.displayMetrics.density).roundToInt()

fun <T> lazyUnsynchronized(initializer: () -> T): Lazy<T> =
    lazy(LazyThreadSafetyMode.NONE, initializer)

fun <T : Parcelable> Fragment.lazyParcelableArgument(propertyName: String) = lazyUnsynchronized {
    requireArguments().getParcelable<T>(propertyName) as T
}

@Suppress("UNCHECKED_CAST")
fun <T : Serializable> Fragment.lazySerializableArgument(propertyName: String) =
    lazyUnsynchronized {
        requireArguments().getSerializable(propertyName) as T
    }

fun Fragment.lazyStringArgument(propertyName: String) = lazyUnsynchronized {
    requireArguments().getString(propertyName)!!
}

fun Fragment.lazyIntArgument(propertyName: String) = lazyUnsynchronized {
    requireArguments().getInt(propertyName)
}

fun Fragment.lazyBooleanArgument(propertyName: String) = lazyUnsynchronized {
    requireArguments().getBoolean(propertyName)
}

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

fun <T : Parcelable> nullableParcelableArgument() = object : ReadWriteProperty<Fragment, T?> {
    override fun getValue(thisRef: Fragment, property: KProperty<*>): T? =
        thisRef.arguments?.getParcelable(property.name) as T?

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T?) =
        thisRef.ensureArguments().putParcelable(property.name, value)
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