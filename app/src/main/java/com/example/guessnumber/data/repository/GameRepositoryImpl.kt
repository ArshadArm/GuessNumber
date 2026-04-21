package com.example.guessnumber.data.repository

import com.example.guessnumber.domain.model.GameResult
import com.example.guessnumber.domain.repository.GameRepository
import kotlin.random.Random

class GameRepositoryImpl : GameRepository {

    private var targetNumber = 0
    private var attempts = 0
    private var maxAttempts = 5
    private var isGameOver = false

    override fun startGame(difficulty: Int) {
        targetNumber = Random.nextInt(1, 101)
        attempts = 0
        isGameOver = false

        maxAttempts = when (difficulty) {
            1 -> 10
            2 -> 5
            3 -> 3
            else -> 5
        }
    }

    override fun checkGuess(guess: Int): GameResult {

        if (isGameOver) {
            return GameResult.Lose(targetNumber)
        }

        attempts++

        val remaining = maxAttempts - attempts

        if (guess == targetNumber) {
            isGameOver = true
            return GameResult.Win(attempts)
        }

        if (attempts >= maxAttempts) {
            isGameOver = true
            return GameResult.Lose(targetNumber)
        }

        return if (guess > targetNumber) {
            GameResult.Lower(remaining)
        } else {
            GameResult.Higher(remaining)
        }
    }

    override fun getRemainingAttempts(): Int {
        return attempts
    }
}