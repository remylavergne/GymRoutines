package com.noahjutz.gymroutines.ui

import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.ListItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import com.noahjutz.gymroutines.data.domain.FullRoutine

@Composable
fun RoutinesScreen(
    routines: LiveData<List<FullRoutine>>
) {
    val routinesList = routines.observeAsState()
    LazyColumnFor(items = routinesList.value ?: emptyList()) { routine ->
        ListItem(text = routine.routine.name, onClick = {})
    }
}