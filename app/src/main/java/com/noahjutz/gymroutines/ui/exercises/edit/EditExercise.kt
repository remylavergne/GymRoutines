package com.noahjutz.gymroutines.ui.exercises.edit

import androidx.compose.foundation.Icon
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import com.noahjutz.gymroutines.data.domain.Exercise

@Composable
fun EditExercise(navBack: () -> Unit ) {
    val viewModel = viewModel<EditExerciseViewModel>()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(viewModel.exercise.name) },
                navigationIcon = {
                    IconButton(
                        onClick = navBack,
                        icon = { Icon(Icons.Filled.ArrowBack) }
                    )
                }
            )
        },
        bodyContent = {
            BodyContent(viewModel.exercise)
        }
    )
}

@Composable
private fun BodyContent(
    exercise: Exercise
) {
    val name = remember { mutableStateOf(exercise.name) }
    val description = remember { mutableStateOf(exercise.description) }

    val logReps = remember { mutableStateOf(exercise.logReps) }
    val logDistance = remember { mutableStateOf(exercise.logDistance) }
    val logWeight = remember { mutableStateOf(exercise.logWeight) }
    val logTime = remember { mutableStateOf(exercise.logWeight) }

    ScrollableColumn {
        TextField(
            value = name.value,
            onValueChange = { name.value = it },
            label = { Text("Name") },
            modifier = Modifier.padding(16.dp).fillMaxWidth()
        )
        OutlinedTextField(
            value = description.value,
            onValueChange = { description.value = it },
            label = { Text("Description") },
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp).fillMaxWidth()
        )
        Divider(modifier = Modifier.padding(bottom = 16.dp))
        LabeledCheckbox(label = "Reps", stateValue = logReps)
        LabeledCheckbox(label = "Time", stateValue = logTime)
        LabeledCheckbox(label = "Distance", stateValue = logDistance)
        LabeledCheckbox(label = "Weight", stateValue = logWeight)
    }
}

@Composable
private fun LabeledCheckbox(label: String, stateValue: MutableState<Boolean>) {
    Row(
        modifier = Modifier.clickable(onClick = { stateValue.value = !stateValue.value })
            .fillMaxWidth()
    ) {
        Checkbox(
            checked = stateValue.value,
            onCheckedChange = { stateValue.value = it },
            modifier = Modifier.padding(16.dp)
        )
        Text(text = label, modifier = Modifier.gravity(Alignment.CenterVertically))
    }
}
