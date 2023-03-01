package com.game.ballsofwool.ext

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner

interface FragmentResult : Parcelable

/**
 * Установка коллбэка
 */
inline fun <reified T : FragmentResult> Fragment.setResultListener(
    crossinline listener: (T) -> Unit,
) = parentFragmentManager.setResultListener(this, listener)

inline fun <reified T : FragmentResult> FragmentManager.setResultListener(
    lifecycleOwner: LifecycleOwner,
    crossinline listener: (T) -> Unit,
) = setFragmentResultListener(
    T::class.qualifiedName!!, lifecycleOwner
) { _, result ->
    listener(result.fromBundle())
}

/**
 * Установка результата
 */
inline fun <reified T : FragmentResult> Fragment.setResult(result: T) =
    parentFragmentManager.setFragmentResult(
        T::class.qualifiedName!!,
        result.toBundle()
    )

/**
 * Упаковка результата в бандл
 */
inline fun <reified T : FragmentResult> T.toBundle() =
    Bundle().also {
        it.putParcelable(T::class.qualifiedName!!, this)
    }

/**
 * Распаковка результата из бандла
 */
inline fun <reified T : FragmentResult> Bundle.fromBundle(): T =
    getParcelable(T::class.qualifiedName!!)!!
