package com.example.guessnumber.domain.usecase

import com.example.guessnumber.domain.repository.GameRepository


class CheckGuessUseCase(private val repository: GameRepository) {
    operator fun invoke(guess: Int) = repository.checkGuess(guess)
}
