package com.game.ballsofwool.data.db

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirebaseDatabaseRepositoryImpl() : Database {

    private val database: FirebaseFirestore = Firebase.firestore

    override fun getLevels(): Task<QuerySnapshot> {
        val reference = database.collection(TABLE_NAME)
        return reference.get()
    }

    override fun getLevel(number: Int): Query {
        val reference = database
            .collection(TABLE_NAME)
            .whereEqualTo(FIELD_LEVEL_NUMBER, number)
        return reference
    }

    override fun getLevelsCount(): Task<AggregateQuerySnapshot> {
        val reference = database
            .collection(TABLE_NAME)
            .count()
        return reference.get(AggregateSource.SERVER)
    }

    private companion object {

        private const val TABLE_NAME = "levels"
        private const val FIELD_LEVEL_NUMBER = "levelNumber"
    }

}