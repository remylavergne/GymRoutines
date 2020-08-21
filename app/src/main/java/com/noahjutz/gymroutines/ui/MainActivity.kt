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
                    Content(Routing.MainScreen)
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
            if (routing == Routing.MainScreen) backStack.newRoot(Routing.MainScreen)
            else backStack.push(routing)
        }
        when (val routing = backStack.last()) {
            is Routing.MainScreen -> {
                Main(navTo)
            }
            is Routing.EditExercise -> {
                ArgsStorage.args[ArgsStorage.Keys.EXERCISE_ID] = routing.exerciseId
                EditExercise(navTo)
            }
            is Routing.EditRoutine -> {
                ArgsStorage.args[ArgsStorage.Keys.ROUTINE_ID] = routing.routineId
                EditRoutine(navTo)
            }
        }
    }
}

sealed class Routing {
    object MainScreen : Routing() // TODO: Pass which screen as parameter?
    data class EditRoutine(val routineId: Int) : Routing()
    data class EditExercise(val exerciseId: Int) : Routing()
}
