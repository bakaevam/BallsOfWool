package com.game.ballsofwool.utils

import com.game.ballsofwool.data.model.LevelResponse
import com.google.firebase.firestore.DocumentSnapshot

class DataConverter {

    companion object {

        fun convertSnapshotToLevelResponse(document: DocumentSnapshot): LevelResponse? {
            return document.toObject(LevelResponse::class.java)
        }
    }
}