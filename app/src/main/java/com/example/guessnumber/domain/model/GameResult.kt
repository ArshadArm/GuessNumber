package com.example.guessnumber.domain.model

sealed class GameResult {

    data class Win(
        val attempts: Int
    ) : GameResult()

    data class Lose(
        val number: Int
    ) : GameResult()

    data class Higher(
        val remainingAttempts: Int
    ) : GameResult()

    data class Lower(
        val remainingAttempts: Int
    ) : GameResult()
}
