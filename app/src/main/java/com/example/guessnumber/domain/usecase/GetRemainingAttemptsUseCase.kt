package com.example.guessnumber.domain.usecase

import com.example.guessnumber.domain.repository.GameRepository

class GetRemainingAttemptsUseCase(
    private val repository: GameRepository
) {
    operator fun invoke() = repository.getRemainingAttempts()
}