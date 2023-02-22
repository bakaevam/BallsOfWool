package com.example.ballsofwool.ext

import android.content.Context
import android.os.Parcelable
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.io.Serializable
import kotlin.math.roundToInt

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