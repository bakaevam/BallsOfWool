package com.game.ballsofwool.utils

import android.util.DisplayMetrics
import android.util.TypedValue
import com.game.ballsofwool.data.model.LevelResponse
import com.google.firebase.firestore.DocumentSnapshot

class DataConverter {

    companion object {

        fun convertSnapshotToLevelResponse(document: DocumentSnapshot): LevelResponse? {
            return document.toObject(LevelResponse::class.java)
        }

        fun Float.dpToPx(displayMetrics: DisplayMetrics): Float =
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, displayMetrics)
    }
}