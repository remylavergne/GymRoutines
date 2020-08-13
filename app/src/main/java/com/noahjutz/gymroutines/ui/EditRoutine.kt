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
import com.noahjutz.gymroutines.data.domain.FullRoutine

@Composable
fun EditRoutine(
    routine: FullRoutine,
    navBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(routine.routine.name) },
                navigationIcon = {
                    IconButton(
                        onClick = navBack,
                        icon = { Icon(Icons.Filled.ArrowBack) }
                    )
                }
            )
        },
        bodyContent = { BodyContent(routine.routine.description) }
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
