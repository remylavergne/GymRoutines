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
import com.noahjutz.gymroutines.data.domain.Routine

enum class TopLevelDestinations { Routines, Exercises }

@Composable
fun Main(
    routines: LiveData<List<FullRoutine>>,
    exercises: LiveData<List<Exercise>>,
    editRoutine: (FullRoutine) -> Unit,
    editExercise: (Exercise) -> Unit
) {
    // TODO: Use compose-router here
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
                    editRoutine(FullRoutine(Routine(), emptyList()))
                else editExercise(Exercise())
            }, icon = { Icon(Icons.Filled.Add) })
        },
        bodyContent = {
            when (screen.value) {
                TopLevelDestinations.Routines -> RoutinesScreen(routines, editRoutine)
                TopLevelDestinations.Exercises -> ExercisesScreen(exercises, editExercise)
            }
        }
    )
}
