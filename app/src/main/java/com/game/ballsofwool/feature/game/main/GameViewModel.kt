package com.game.ballsofwool.feature.game.main

import android.util.Log
import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.viewModelScope
import com.game.ballsofwool.data.db.FirebaseDatabaseRepositoryImpl
import com.game.ballsofwool.data.model.*
import com.game.ballsofwool.data.source.Repository
import com.game.ballsofwool.feature.base.MviViewModel
import com.game.ballsofwool.utils.DataConverter
import com.game.ballsofwool.utils.LinesCrossingCalculator
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class GameViewModel(
    private val levelNumber: Int,
    private val db: FirebaseDatabaseRepositoryImpl,
    private val repository: Repository,
) : MviViewModel<GameState, GameEffect>(GameState()) {

    init {
        getAllLevelsCount()
        getLevel()
    }

    fun onRestartClick() = getLevel()

    fun onNextLevelClick() = getNextLevel()

    fun onBallDrag(ball: Ball, delta: Offset) {
        val level = state.value.currentLevel ?: return
        setState {
            val actualBalls = level.balls.toMutableList()
            val dragBallIndex = actualBalls.indexOfFirst { it.id == ball.id }

            val dragBall = actualBalls[dragBallIndex]
            val newBall = dragBall.copy(x = dragBall.x + delta.x, y = dragBall.y + delta.y)
            actualBalls[dragBallIndex] = newBall

            copy(
                currentLevel = level.copy(balls = actualBalls)
            )
        }
        validate()
    }

    fun onDragEnd() = checkReadyLevel()

    private fun getLevel() {
        viewModelScope.launch {
            val dbLevels = mutableListOf<LevelResponse>()
            val number = when {
                levelNumber != -1 -> levelNumber
                else -> repository.lastOpenLevel.first()
            }
            db.getLevel(number)
                .addOnFailureListener {
                    Log.e("FIREBASE STORE", it.message.toString())
                }
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val result = it.result.documents
                        result.forEach { doc ->
                            println(doc.data)
                            val response = DataConverter.convertSnapshotToLevelResponse(doc)
                            if (response != null) {
                                dbLevels.add(response)
                            }
                        }
                        val currentLevel = dbLevels.first().toLevel()
                        setState { copy(currentLevel = currentLevel) }
                    }
                }
        }
        validate()
    }

    private fun getNextLevel() {
        viewModelScope.launch {
            val nextLevelNumber = repository.lastOpenLevel.first() + 1
            val state = state.value
            if (state.allLevels != null && nextLevelNumber <= state.allLevels) {
                repository.setLastOpenLevel(nextLevelNumber)
                getLevel()
            } else {
                postEffect(GameEffect.ShowToastAllLevels)
            }
        }
    }

    private fun getAllLevelsCount() {
        viewModelScope.launch {
            db.getLevelsCount()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val allLevels = task.result.count.toInt()
                        setState {
                            copy(allLevels = allLevels)
                        }
                    }
                }
        }
    }

    private fun checkReadyLevel() {
        val crossingLine = state.value.currentLevel?.lines?.find { it.isCrossing }
        if (crossingLine == null) {
            postEffect(GameEffect.ShowComplete)
        }
    }

    private fun validate() {
        setState {
            val crossingLines = when {
                currentLevel != null -> LinesCrossingCalculator.detectCrossing(
                    currentLevel.lines,
                    currentLevel.balls
                )
                else -> emptyList()
            }
            copy(currentLevel = currentLevel?.copy(lines = crossingLines))
        }
    }

    private fun LevelResponse.toLevel(): Level {
        val localLines = lines.toLocalLines()
        return Level(
            levelNumber = levelNumber,
            balls = balls,
            lines = LinesCrossingCalculator.detectCrossing(
                lines = localLines,
                balls = balls,
            )
        )
    }

    private fun List<Line>.toLocalLines(): List<LocalLine> {
        return map { line ->
            LocalLine(
                firstBall = line.firstBall!!,
                secondBall = line.secondBall!!,
            )
        }
    }
}