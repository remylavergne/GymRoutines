package com.noahjutz.gymroutines.ui.routines.edit

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel

@Composable
fun EditRoutine(navBack: () -> Unit) {
    val viewModel = viewModel<EditRoutineViewModel>()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(viewModel.routine.routine.name) },
                navigationIcon = {
                    IconButton(
                        onClick = navBack,
                        icon = { Icon(Icons.Filled.ArrowBack) }
                    )
                }
            )
        },
        bodyContent = {
            BodyContent(viewModel)
        }
    )
}

@Suppress("NAME_SHADOWING")
@Composable
private fun BodyContent(
    viewModel: EditRoutineViewModel
) {
    val name = remember { mutableStateOf(viewModel.routine.routine.name) }
    val description = remember { mutableStateOf(viewModel.routine.routine.description) }
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
            items = viewModel.routine.exercises,
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
                            text = { Text("Set ${index + 1}") },
                            secondaryText = { Text(set.toString()) },
                        )
                    }
                    Row(modifier = Modifier.gravity(Alignment.End)) {
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

