package com.noahjutz.gymroutines.ui

import android.os.Bundle
import androidx.activity.viewModels
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
import com.noahjutz.gymroutines.data.domain.Exercise
import com.noahjutz.gymroutines.data.domain.FullRoutine
import com.noahjutz.gymroutines.ui.exercises.ExercisesViewModel
import com.noahjutz.gymroutines.ui.exercises.edit.EditExercise
import com.noahjutz.gymroutines.ui.exercises.edit.EditExerciseViewModel
import com.noahjutz.gymroutines.ui.routines.RoutinesViewModel
import com.noahjutz.gymroutines.ui.routines.edit.EditRoutine
import com.noahjutz.gymroutines.ui.routines.edit.EditRoutineViewModel
import com.noahjutz.gymroutines.util.ArgsStorage
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val backPressHandler = BackPressHandler()

    private val editRoutineViewModel: EditRoutineViewModel by viewModels()
    private val editExerciseViewModel: EditExerciseViewModel by viewModels()
    private val routinesViewModel: RoutinesViewModel by viewModels()
    private val exercisesViewModel: ExercisesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colors = if (isSystemInDarkTheme()) darkColors() else lightColors()) {
                Providers(
                    AmbientBackPressHandler provides backPressHandler
                ) {
                    Content(
                        defaultRouting = Routing.MainScreen,
                        editRoutineViewModel = editRoutineViewModel,
                        editExerciseViewModel = editExerciseViewModel,
                        routinesViewModel = routinesViewModel,
                        exercisesViewModel = exercisesViewModel
                    )
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
fun Content(
    defaultRouting: Routing,
    editRoutineViewModel: EditRoutineViewModel,
    editExerciseViewModel: EditExerciseViewModel,
    routinesViewModel: RoutinesViewModel,
    exercisesViewModel: ExercisesViewModel
) {
    Router(defaultRouting) { backStack ->
        when (val routing = backStack.last()) {
            is Routing.MainScreen -> Main(
                routinesViewModel = routinesViewModel,
                exercisesViewModel = exercisesViewModel,
                navTo = { backStack.push(it) }
            )
            is Routing.EditExercise -> {
                ArgsStorage.args[ArgsStorage.Keys.EXERCISE_ID] = routing.exerciseId
                EditExercise(
                    viewModel = editExerciseViewModel,
                    navBack = { backStack.pop() }
                )
            }
            is Routing.EditRoutine -> {
                ArgsStorage.args[ArgsStorage.Keys.ROUTINE_ID] = routing.routineId
                EditRoutine(
                    viewModel = editRoutineViewModel,
                    navBack = { backStack.pop() }
                )
            }
        }
    }
}

sealed class Routing {
    object MainScreen : Routing() // TODO: Pass which screen as parameter?
    data class EditRoutine(val routineId: Int) : Routing()
    data class EditExercise(val exerciseId: Int) : Routing()
}
