package com.example.guessnumber.domain.usecase


import com.example.guessnumber.domain.repository.GameRepository

class StartGameUseCase(private val repository: GameRepository) {
    operator fun invoke(difficulty: Int) {
        repository.startGame(difficulty)
    }
}