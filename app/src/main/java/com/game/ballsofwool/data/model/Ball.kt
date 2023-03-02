package com.game.ballsofwool.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ball(
    val id: Int = 0,
    val x: Float = 0F,
    val y: Float = 0F,
) : Parcelable