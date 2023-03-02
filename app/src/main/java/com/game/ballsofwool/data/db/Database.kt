package com.game.ballsofwool.data.db

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.AggregateQuerySnapshot
import com.google.firebase.firestore.QuerySnapshot

interface Database {

    fun getLevels(): Task<QuerySnapshot>

    fun getLevel(number: Int): Task<QuerySnapshot>

    fun getLevelsCount(): Task<AggregateQuerySnapshot>
}