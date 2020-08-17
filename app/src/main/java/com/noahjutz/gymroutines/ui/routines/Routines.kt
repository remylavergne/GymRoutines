package com.noahjutz.gymroutines.ui.routines

import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import com.noahjutz.gymroutines.data.domain.FullRoutine

@Composable
fun RoutinesScreen(
    viewModel: RoutinesViewModel,
    editRoutine: (FullRoutine) -> Unit
) {
    val routinesList = viewModel.routines.observeAsState()
    LazyColumnFor(items = routinesList.value ?: emptyList()) { routine ->
        ListItem(
            text = routine.routine.name,
            secondaryText = routine.routine.description.let { if (it.isEmpty()) null else it },
            onClick = { editRoutine(routine) }
        )
    }
}