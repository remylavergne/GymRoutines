package com.noahjutz.gymroutines.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Providers
import androidx.compose.ui.platform.setContent
import androidx.lifecycle.LiveData
import com.github.zsoltk.compose.backpress.AmbientBackPressHandler
import com.github.zsoltk.compose.backpress.BackPressHandler
import com.github.zsoltk.compose.router.Router
import com.noahjutz.gymroutines.data.domain.Exercise
import com.noahjutz.gymroutines.data.domain.FullRoutine
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private val backPressHandler = BackPressHandler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colors = if (isSystemInDarkTheme()) darkColors() else lightColors()) {
                Providers(
                    AmbientBackPressHandler provides backPressHandler
                ) {
                    Content(
                        Routing.MainScreen,
                        viewModel.routines,
                        viewModel.exercises
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
    routines: LiveData<List<FullRoutine>>,
    exercises: LiveData<List<Exercise>>
) {
    Router(defaultRouting) { backStack ->
        when (val routing = backStack.last()) {
            is Routing.MainScreen -> Main(
                routines = routines,
                exercises = exercises,
                editRoutine = { backStack.push(Routing.EditRoutine(it)) },
                editExercise = { backStack.push(Routing.EditExercise(it)) }
            )
            is Routing.EditExercise -> Surface { Text(routing.exercise.name) }
            is Routing.EditRoutine -> Surface { Text(routing.routine.routine.name) }
        }
    }
}

sealed class Routing {
    object MainScreen : Routing()
    data class EditRoutine(val routine: FullRoutine) : Routing()
    data class EditExercise(val exercise: Exercise) : Routing()
}
