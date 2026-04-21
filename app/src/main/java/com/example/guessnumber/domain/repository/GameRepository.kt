package com.example.guessnumber.domain.repository

import com.example.guessnumber.domain.model.GameResult

interface GameRepository {
    fun startGame(difficulty: Int)
    fun checkGuess(guess: Int): GameResult
    fun getRemainingAttempts(): Int
}
