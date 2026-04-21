package com.example.guessnumber.presentation.state

data class GameState(
    val message: String = "Select difficulty",
    val attemptsLeft: Int = 0,
    val isGameOver: Boolean = false,
    val showDialog: Boolean = true
)
