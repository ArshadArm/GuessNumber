package com.example.guessnumber.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.guessnumber.data.repository.GameRepositoryImpl
import com.example.guessnumber.domain.usecase.CheckGuessUseCase
import com.example.guessnumber.domain.usecase.StartGameUseCase
import com.example.guessnumber.presentation.viewmodel.GameViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = GameRepositoryImpl()
        val viewModel = GameViewModel(
            StartGameUseCase(repository),
            CheckGuessUseCase(repository)
        )

        setContent {
            GameScreen(viewModel)
        }
    }
}