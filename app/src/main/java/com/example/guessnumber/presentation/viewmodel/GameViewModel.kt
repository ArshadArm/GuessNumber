package com.example.guessnumber.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.guessnumber.domain.model.GameResult
import com.example.guessnumber.domain.usecase.CheckGuessUseCase
import com.example.guessnumber.domain.usecase.StartGameUseCase
import com.example.guessnumber.presentation.state.GameState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class GameViewModel(
    private val startGame: StartGameUseCase,
    private val checkGuess: CheckGuessUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(GameState())
    val state: StateFlow<GameState> = _state

    fun start(difficulty: Int) {
        startGame(difficulty)

        _state.value = GameState(
            message = "Game Started! Guess 1-100",
            attemptsLeft = when (difficulty) {
                1 -> 10
                2 -> 5
                3 -> 3
                else -> 5
            },
            showDialog = false
        )
    }

    fun guess(number: Int) {

        when (val result = checkGuess(number)) {

            is GameResult.Win -> {
                _state.value = _state.value.copy(
                    message = "🎉 Won in ${result.attempts}",
                    attemptsLeft = 0,
                    isGameOver = true
                )
            }

            is GameResult.Lose -> {
                _state.value = _state.value.copy(
                    message = "❌ Lost! Number was ${result.number}",
                    attemptsLeft = 0,
                    isGameOver = true
                )
            }

            is GameResult.Higher -> {
                _state.value = _state.value.copy(
                    message = "Try Higher",
                    attemptsLeft = result.remainingAttempts
                )
            }

            is GameResult.Lower -> {
                _state.value = _state.value.copy(
                    message = "Try Lower",
                    attemptsLeft = result.remainingAttempts
                )
            }
        }
    }

    fun resetGame() {
        _state.value = GameState(showDialog = true)
    }
}