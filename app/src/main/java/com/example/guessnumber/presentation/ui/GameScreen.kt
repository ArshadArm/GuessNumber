package com.example.guessnumber.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.guessnumber.presentation.ui.components.DifficultyDialog
import com.example.guessnumber.presentation.viewmodel.GameViewModel

@Composable
fun GameScreen(viewModel: GameViewModel) {

    val state by viewModel.state.collectAsState()
    var input by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }

    if (state.showDialog) {
        DifficultyDialog { difficulty ->
            viewModel.start(difficulty)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "🎯 Number Guessing Game",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = state.message,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = input,
            onValueChange = {
                input = it
                isError = false
            },
            label = { Text("Enter number (1-100)") },
            isError = isError,
            singleLine = true,
            enabled = !state.isGameOver
        )

        if (isError) {
            Text(
                text = "Enter a valid number!",
                color = MaterialTheme.colorScheme.error
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val number = input.toIntOrNull()
                if (number == null || number !in 1..100) {
                    isError = true
                } else {
                    viewModel.guess(number)
                    input = ""
                }
            },
            enabled = !state.isGameOver
        ) {
            Text("Guess")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Attempts Left: ${state.attemptsLeft}")

        if (state.isGameOver) {
            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = {
                viewModel.resetGame()
            }) {
                Text("Play Again")
            }
        }
    }
}