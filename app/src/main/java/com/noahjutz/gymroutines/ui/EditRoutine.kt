package com.noahjutz.gymroutines.ui

import androidx.compose.foundation.Icon
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.foundation.lazy.LazyColumnForIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.state
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.noahjutz.gymroutines.data.domain.ExerciseImpl
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
        bodyContent = {
            BodyContent(
                name = routine.routine.name,
                description = routine.routine.description,
                exercises = routine.exercises
            )
        }
    )
}

@Suppress("NAME_SHADOWING")
@Composable
private fun BodyContent(
    name: String,
    description: String,
    exercises: List<ExerciseImpl>
) {
    val name = state { name }
    val description = state { description }
    ScrollableColumn { // TODO: fix performance issues
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
            modifier = Modifier.padding(bottom = 16.dp, start = 16.dp, end = 16.dp).fillMaxWidth()
        )
        Divider(modifier = Modifier.padding(bottom = 16.dp))
        LazyColumnFor(
            items = exercises,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        ) { exercise ->
            Card(
                modifier = Modifier.padding(bottom = 16.dp).fillParentMaxWidth()
            ) {
                Column(modifier = Modifier.clickable(onClick = {})) {
                    Text(text = exercise.exercise.name, modifier = Modifier.padding(16.dp))
                    LazyColumnForIndexed(
                        items = exercise.sets,
                        modifier = Modifier.padding(bottom = 16.dp)
                    ) { index, set ->
                        ListItem(
                            text = "Set ${index + 1}",
                            secondaryText = set.toString(),
                            onClick = {})
                    }
                    Row(modifier= Modifier.gravity(Alignment.End)) {
                        IconButton(
                            icon = { Icon(Icons.Filled.Add) },
                            onClick = {},
                            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                        )
                    }
                }
            }
        }
        Button(
            content = { Text("Add Exercise") },
            onClick = {},
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .gravity(Alignment.CenterHorizontally)
        )
    }
}

