package com.noahjutz.gymroutines.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.ViewAgenda
import androidx.compose.runtime.Composable
import androidx.compose.runtime.state
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.LiveData
import com.noahjutz.gymroutines.data.domain.Exercise
import com.noahjutz.gymroutines.data.domain.FullRoutine
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colors = if (isSystemInDarkTheme()) darkColors() else lightColors()) {
                Main(viewModel.routines, viewModel.exercises)
            }
        }
    }
}

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