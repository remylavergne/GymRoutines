package com.noahjutz.gymroutines.ui.routines

import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.noahjutz.gymroutines.ui.Routing

@Composable
fun RoutinesScreen(
    viewModel: RoutinesViewModel,
    navTo: (Routing) -> Unit
) {
    val routinesList = viewModel.routines.observeAsState()
    LazyColumnFor(items = routinesList.value ?: emptyList()) { routine ->
        ListItem(
            text = routine.routine.name,
            secondaryText = routine.routine.description.let { if (it.isEmpty()) null else it },
            onClick = { navTo(Routing.EditRoutine(routine)) }
        )
    }
}