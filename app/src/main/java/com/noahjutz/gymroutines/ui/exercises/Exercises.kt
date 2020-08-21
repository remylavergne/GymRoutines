package com.noahjutz.gymroutines.ui.exercises

import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.noahjutz.gymroutines.ui.Routing

@Composable
fun ExercisesScreen(
    viewModel: ExercisesViewModel,
    navTo: (Routing) -> Unit
) {
    val exerciseList = viewModel.exercises.observeAsState()
    LazyColumnFor(items = exerciseList.value?.filter { !it.hidden } ?: emptyList()) { exercise ->
        ListItem(
            text = { Text(exercise.name.takeIf { it.isNotBlank() } ?: "Unnamed") },
            secondaryText = { Text(exercise.description) },
            modifier = Modifier.clickable(onClick = { navTo(Routing.EditExercise(exercise.exerciseId)) })
        )
    }
}