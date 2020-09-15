package com.noahjutz.gymroutines.ui.routines.edit

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.IconButton
import androidx.compose.material.ListItem
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.noahjutz.gymroutines.data.domain.ExerciseHolder
import com.noahjutz.gymroutines.data.domain.ExerciseImpl
import com.noahjutz.gymroutines.ui.Routing

@Composable
fun PickExercise(
    navTo: (Routing) -> Unit,
    viewModel: EditRoutineViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pick Exercise") },
                navigationIcon = {
                    IconButton(
                        onClick = {},
                        icon = { Icon(Icons.Filled.ArrowBack) }
                    )
                }
            )
        },
        bodyContent = { BodyContent(viewModel) }
    )
}

@Composable
private fun BodyContent(
    viewModel: EditRoutineViewModel
) {
    val exercises by viewModel.exercises.observeAsState()
    LazyColumnFor(items = exercises ?: emptyList()) { exercise ->
        ListItem(
            text = { Text(exercise.name) },
            secondaryText = { Text(exercise.description) },
            modifier = Modifier.clickable(onClick = {
                viewModel.addExercise(
                    ExerciseImpl(
                        exercise = exercise,
                        sets = emptyList(),
                        exerciseHolder = ExerciseHolder(
                            exerciseId = exercise.exerciseId,
                            routineId = viewModel.fullRoutine.routine.routineId
                        )
                    )
                )
            })
        )
    }
}