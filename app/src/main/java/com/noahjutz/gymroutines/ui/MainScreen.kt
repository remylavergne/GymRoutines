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
import com.noahjutz.gymroutines.data.domain.Exercise
import com.noahjutz.gymroutines.data.domain.FullRoutine
import com.noahjutz.gymroutines.data.domain.Routine
import com.noahjutz.gymroutines.ui.exercises.ExercisesScreen
import com.noahjutz.gymroutines.ui.exercises.ExercisesViewModel
import com.noahjutz.gymroutines.ui.routines.RoutinesScreen
import com.noahjutz.gymroutines.ui.routines.RoutinesViewModel

enum class TopLevelDestinations { Routines, Exercises }

@Composable
fun Main(
    routinesViewModel: RoutinesViewModel,
    exercisesViewModel: ExercisesViewModel,
    navTo: (Routing) -> Unit
) {
    // TODO: Use compose-router here?
    val screen = state { TopLevelDestinations.Routines }
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
            FloatingActionButton(onClick = {
                if (screen.value == TopLevelDestinations.Routines)
                    navTo(Routing.EditRoutine(FullRoutine(Routine(), emptyList())))
                else navTo(Routing.EditExercise(Exercise()))
            }, icon = { Icon(Icons.Filled.Add) })
        },
        bodyContent = {
            when (screen.value) {
                TopLevelDestinations.Routines -> RoutinesScreen(routinesViewModel, navTo)
                TopLevelDestinations.Exercises -> ExercisesScreen(exercisesViewModel, navTo)
            }
        }
    )
}
