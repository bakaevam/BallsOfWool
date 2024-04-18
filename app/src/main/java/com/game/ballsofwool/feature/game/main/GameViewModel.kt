package com.game.ballsofwool.feature.game.main

import android.util.DisplayMetrics
import androidx.compose.ui.geometry.Offset
import androidx.lifecycle.viewModelScope
import com.game.ballsofwool.data.db.Database
import com.game.ballsofwool.data.model.Ball
import com.game.ballsofwool.data.model.Level
import com.game.ballsofwool.data.model.LevelResponse
import com.game.ballsofwool.data.model.Line
import com.game.ballsofwool.data.model.LocalLine
import com.game.ballsofwool.data.source.Repository
import com.game.ballsofwool.feature.base.MviViewModel
import com.game.ballsofwool.utils.DataConverter
import com.game.ballsofwool.utils.DataConverter.Companion.dpToPx
import com.game.ballsofwool.utils.LinesCrossingCalculator
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import timber.log.Timber

class GameViewModel(
    private var levelNumber: Int,
    private val db: Database,
    private val repository: Repository,
) : MviViewModel<GameState, GameEffect>(GameState()) {

    private var level: Level? = null
    private var allLevelsCount: Int? = null

    init {
        getAllLevelsCount()
        getLevel()
    }

    fun onRestartLoadClick() = getLevel()

    fun onRestartAllLevelsClick() = getAllLevelsCount()

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

    fun onBallsLoaded(displayMetrics: DisplayMetrics) {
        setState {
            copy(
                currentLevel = level?.copy(
                    balls = level?.balls?.toPxBalls(
                        displayMetrics
                    ) ?: emptyList()
                ),
                loading = false,
            )
        }
        validate()
        showManualDialog()
    }

    private fun showManualDialog() {
        viewModelScope.launch {
            val showedManual = !repository.showedManual.first()
            if (state.value.currentLevel?.levelNumber == FIRST_LEVEL_NUMBER && showedManual) {
                repository.setShowedManual(true)
                val soundOn = repository.soundOn.first()
                postEffect(GameEffect.ShowManualDialog(soundOn))
            }
        }
    }

    private fun getLevel() {
        viewModelScope.launch {
            val dbLevels = mutableListOf<LevelResponse>()
            val number = getLevelNumber()
            db.getLevel(number)
                .addSnapshotListener { value, error ->
                    try {
                        setState { copy(loading = true, loadError = null) }
                        when {
                            error != null -> {
                                setState { copy(loadError = error) }
                                return@addSnapshotListener
                            }
                            value != null -> {
                                val result = value.documents
                                result.forEach { doc ->
                                    println(doc)
                                    val response = DataConverter.convertSnapshotToLevelResponse(doc)
                                    if (response != null) {
                                        dbLevels.add(response)
                                    }
                                }
                                if (dbLevels.isEmpty()) {
                                    setState { copy(loadError = Throwable()) }
                                    return@addSnapshotListener
                                } else {
                                    level = dbLevels.first().toLevel()
                                    levelNumber = level?.levelNumber ?: -1
                                    postEffect(GameEffect.BallsLoaded)
                                }
                            }
                        }
                    } catch (e: Throwable) {
                        setState { copy(loadError = e) }
                        Timber.e(e, "error get level in Game")
                    } finally {
                        setState { copy(loading = false) }
                    }
                }
        }
    }

    private suspend fun getLevelNumber(): Int {
        return when {
            levelNumber != -1 -> levelNumber
            else -> repository.lastOpenLevel.first()
        }
    }

    private fun getNextLevel() {
        viewModelScope.launch {
            if (allLevelsCount != null && level != null && level!!.levelNumber < allLevelsCount!!) {
                val nextLevelNumber = repository.lastOpenLevel.first() + 1
                levelNumber = if (level?.levelNumber == repository.lastOpenLevel.first()) {
                    repository.setLastOpenLevel(nextLevelNumber)
                    nextLevelNumber
                } else {
                    level?.levelNumber!! + 1
                }
                getLevel()
            } else {
                postEffect(GameEffect.ShowToastAllLevels)
            }
        }
    }

    private fun getAllLevelsCount() {
        viewModelScope.launch {
            db.getLevelsCount()
                .addOnFailureListener { e ->
                    Timber.e(e, "error load all levels count")
                    setState { copy(allLevelsError = e, loading = false) }
                }
                .addOnCompleteListener { task ->
                    setState { copy(loading = true, allLevelsError = null) }
                    if (task.isSuccessful) {
                        allLevelsCount = task.result.count.toInt()
                        setState { copy(allLevels = allLevelsCount) }
                        setState { copy(loading = false) }
                    } else {
                        Timber.e(task.exception, "error load all levels count")
                        setState { copy(allLevelsError = task.exception, loading = false) }
                    }
                }
        }
    }

    private fun checkReadyLevel() {
        val crossingLine = state.value.currentLevel?.lines?.find { it.isCrossing }
        if (crossingLine == null) {
            viewModelScope.launch {
                postEffect(GameEffect.ShowComplete(repository.soundOn.first()))
            }
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

    private fun List<Ball>.toPxBalls(displayMetrics: DisplayMetrics): List<Ball> {
        return map { ball ->
            ball.copy(
                x = ball.x.dpToPx(displayMetrics),
                y = ball.y.dpToPx(displayMetrics)
            )
        }
    }

    companion object {

        private const val FIRST_LEVEL_NUMBER = 1
    }
}