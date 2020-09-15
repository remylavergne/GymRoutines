package com.noahjutz.gymroutines.ui

import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.ViewAgenda
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.noahjutz.gymroutines.ui.exercises.ExercisesScreen
import com.noahjutz.gymroutines.ui.routines.RoutinesScreen

enum class TopLevelDestinations { Routines, Exercises }

@Composable
fun Main(
    navTo: (Routing) -> Unit
) {
    // TODO: Use compose-router here?
    val screen = remember { mutableStateOf(TopLevelDestinations.Routines) }
    Scaffold(
        topBar = { TopAppBar(title = { Text("GymRoutines") }) },
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
            FloatingActionButton(
                onClick = {
                    if (screen.value == TopLevelDestinations.Routines)
                        navTo(Routing.EditRoutine(-1))
                    else navTo(Routing.EditExercise(-1))
                },
                icon = { Icon(Icons.Filled.Add) }
            )
        },
        bodyContent = {
            when (screen.value) {
                TopLevelDestinations.Routines -> RoutinesScreen(navTo)
                TopLevelDestinations.Exercises -> ExercisesScreen(navTo)
            }
        }
    )
}
