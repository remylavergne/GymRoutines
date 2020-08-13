package com.noahjutz.gymroutines.ui

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.ViewAgenda
import androidx.compose.runtime.Composable
import androidx.compose.runtime.state
import androidx.lifecycle.LiveData
import com.noahjutz.gymroutines.data.domain.Exercise
import com.noahjutz.gymroutines.data.domain.FullRoutine

enum class TopLevelDestinations { Routines, Exercises }

@Composable
fun Main(
    routines: LiveData<List<FullRoutine>>,
    exercises: LiveData<List<Exercise>>
) {
    val screen = state { TopLevelDestinations.Routines }
    Scaffold(
        topBar = { TopAppBar(title = { Text("Title") }) },
        bottomBar = {
            BottomNavigation {
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.ViewAgenda) },
                    selected = screen.value == TopLevelDestinations.Routines,
                    onSelect = { screen.value = TopLevelDestinations.Routines },
                    label = { Text("Routines") }
                )
                BottomNavigationItem(
                    icon = { Icon(Icons.Filled.DirectionsRun) },
                    selected = screen.value == TopLevelDestinations.Exercises,
                    onSelect = { screen.value = TopLevelDestinations.Exercises },
                    label = { Text("Exercises") }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}, icon = { Icon(Icons.Filled.Add) })
        },
        bodyContent = {
            when (screen.value) {
                TopLevelDestinations.Routines -> RoutinesScreen(routines)
                TopLevelDestinations.Exercises -> ExercisesScreen(exercises)
            }
        }
    )
}
