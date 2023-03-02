package com.game.ballsofwool.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Line(
    val firstBall: Int? = 0,
    val secondBall: Int? = 0,
) : Parcelable