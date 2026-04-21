package com.example.guessnumber.presentation.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DifficultyDialog(onSelect: (Int) -> Unit) {
    AlertDialog(
        onDismissRequest = {},
        title = { Text("Select Difficulty") },
        text = {
            Column {
                Button(onClick = { onSelect(1) }) {
                    Text("Easy (10 Attempts)")
                }
                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = { onSelect(2) }) {
                    Text("Medium (5 Attempts)")
                }
                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = { onSelect(3) }) {
                    Text("Hard (3 Attempts)")
                }
            }
        },
        confirmButton = {}
    )
}