package com.example.ballsofwool.data

import android.content.Context
import javax.inject.Inject

class DefaultLocalDatasource @Inject constructor(context: Context) : LocalDatasource {

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    companion object {
        private const val PREFS_NAME = "PREFS_NAME"
    }
}