package com.noahjutz.gymroutines.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.platform.setContent
import com.github.zsoltk.compose.backpress.AmbientBackPressHandler
import com.github.zsoltk.compose.backpress.BackPressHandler
import com.github.zsoltk.compose.router.Router
import com.noahjutz.gymroutines.ui.exercises.edit.EditExercise
import com.noahjutz.gymroutines.ui.routines.edit.EditRoutine
import com.noahjutz.gymroutines.ui.routines.edit.EditRoutineViewModel
import com.noahjutz.gymroutines.ui.routines.edit.PickExercise
import com.noahjutz.gymroutines.util.ArgsStorage
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val backPressHandler = BackPressHandler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colors = if (isSystemInDarkTheme()) darkColors() else lightColors()) {
                Providers(AmbientBackPressHandler provides backPressHandler) {
                    Content(Routing.MainScreen(TopLevelDestinations.Routines))
                }
            }
        }
    }

    override fun onBackPressed() {
        if (!backPressHandler.handle()) {
            super.onBackPressed()
        }
    }
}

@Composable
fun Content(defaultRouting: Routing) {
    Router(defaultRouting) { backStack ->
        val navTo = { routing: Routing ->
            if (routing is Routing.MainScreen) backStack.newRoot(Routing.MainScreen(routing.tab))
            else backStack.push(routing)
        }
        val navBack: () -> Unit = {
            backStack.pop()
        }
        when (val routing = backStack.last()) {
            is Routing.MainScreen -> {
                Main(navTo, routing.tab)
            }
            is Routing.EditExercise -> {
                ArgsStorage.exerciseId = routing.exerciseId
                EditExercise(navBack)
            }
            is Routing.EditRoutine -> {
                ArgsStorage.routineId = routing.routineId
                EditRoutine(navTo, navBack)
            }
            is Routing.PickExercise -> {
                PickExercise(
                    navBack = navBack,
                    viewModel = routing.viewModel
                )
            }
        }
    }
}

sealed class Routing {
    data class PickExercise(val viewModel: EditRoutineViewModel) : Routing()
    data class MainScreen(val tab: TopLevelDestinations) : Routing()
    data class EditRoutine(val routineId: Int) : Routing()
    data class EditExercise(val exerciseId: Int) : Routing()
}
