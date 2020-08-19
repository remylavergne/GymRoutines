package com.noahjutz.gymroutines.ui.routines

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.IconButton
import androidx.compose.material.ListItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.noahjutz.gymroutines.ui.Routing

@Composable
fun RoutinesScreen(
    viewModel: RoutinesViewModel,
    navTo: (Routing) -> Unit
) {
    val routinesList = viewModel.routines.observeAsState()
    LazyColumnFor(items = routinesList.value ?: emptyList()) { routine ->
        ListItem(
            text = { Text(routine.routine.name.takeIf { it.isNotBlank() } ?: "Unnamed") },
            secondaryText = { routine.routine.description.let { if (it.isNotBlank()) Text(it) } },
            trailing = {
                IconButton(
                    icon = { Icon(Icons.Filled.Delete) },
                    onClick = { viewModel.delete(routine) }
                )
            },
            modifier = Modifier.clickable(onClick = { navTo(Routing.EditRoutine(routine.routine.routineId)) })
        )
    }
}