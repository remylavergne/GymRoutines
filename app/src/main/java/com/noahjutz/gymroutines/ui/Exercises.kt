package com.noahjutz.gymroutines.ui

import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import com.noahjutz.gymroutines.data.domain.Exercise

@Composable
fun ExercisesScreen(
    exercises: LiveData<List<Exercise>>,
    editExercise: (Exercise) -> Unit
) {
    val exerciseList = exercises.observeAsState()
    LazyColumnFor(items = exerciseList.value?.filter { !it.hidden } ?: emptyList()) { exercise ->
        ListItem(
            text = exercise.name,
            secondaryText = exercise.description.let { if (it.isEmpty()) null else it },
            onClick = { editExercise(exercise) }
        )
    }
}