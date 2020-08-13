package com.noahjutz.gymroutines.ui

import androidx.compose.foundation.Box
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.noahjutz.gymroutines.data.domain.Exercise

@Composable
fun EditExercise(
    exercise: Exercise,
    navBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(exercise.name) },
                navigationIcon = {
                    IconButton(
                        onClick = navBack,
                        icon = { Icon(Icons.Filled.ArrowBack) }
                    )
                }
            )
        },
        bodyContent = { BodyContent(exercise.description) }
    )
}

@Composable
private fun BodyContent(
    description: String
) {
    Box(padding = 16.dp) {
        Text(description)
    }
}