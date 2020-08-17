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
import androidx.compose.runtime.state
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
        bodyContent = {
            BodyContent(exercise)
        }
    )
}

@Composable
private fun BodyContent(
    exercise: Exercise
) {
    val name = state { exercise.name }
    val description = state { exercise.description }

    val logReps = state { exercise.logReps }
    val logDistance = state { exercise.logDistance }
    val logWeight = state { exercise.logWeight }
    val logTime = state { exercise.logWeight }

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
        modifier = Modifier.clickable(onClick = { stateValue.value = !stateValue.value }).fillMaxWidth()
    ) {
        Checkbox(
            checked = stateValue.value,
            onCheckedChange = { stateValue.value = it },
            modifier = Modifier.padding(16.dp)
        )
        Text(text = label, modifier = Modifier.gravity(Alignment.CenterVertically))
    }
}
