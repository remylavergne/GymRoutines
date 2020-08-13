package com.noahjutz.gymroutines.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Icon
import androidx.compose.foundation.Text
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsRun
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.ViewAgenda
import androidx.compose.runtime.Composable
import androidx.compose.runtime.state
import androidx.compose.ui.platform.setContent
import androidx.ui.tooling.preview.Preview
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colors = if (isSystemInDarkTheme()) darkColors() else lightColors()) {
                Main()
            }
        }
    }
}

enum class TopLevelDestinations { Routines, Exercises }

@Composable
fun Main() {
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
        bodyContent = {
            when (screen.value) {
                TopLevelDestinations.Routines -> RoutinesScreen()
                TopLevelDestinations.Exercises -> ExercisesScreen()
            }
        }
    )
}

@Preview
@Composable
fun PreviewMain() {
    MaterialTheme(colors = if (isSystemInDarkTheme()) darkColors() else lightColors()) {
        Main()
    }
}