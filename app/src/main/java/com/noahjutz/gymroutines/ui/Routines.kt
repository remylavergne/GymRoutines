package com.noahjutz.gymroutines.ui

import androidx.compose.foundation.Text
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.ui.tooling.preview.Preview

@Composable
fun RoutinesScreen() {
    Text("Routines")
}

@Composable
@Preview
fun PreviewRoutinesScreen() {
    MaterialTheme {
        RoutinesScreen()
    }
}