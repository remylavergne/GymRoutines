package com.noahjutz.gymroutines.ui.exercises.edit

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.noahjutz.gymroutines.data.Repository
import com.noahjutz.gymroutines.data.domain.Exercise
import com.noahjutz.gymroutines.util.ArgsStorage
import com.noahjutz.gymroutines.util.provideExercise

class EditExerciseViewModel @ViewModelInject constructor(private val repository: Repository) :
    ViewModel() {
    val exercise: Exercise
        get() = repository.provideExercise(ArgsStorage.args[ArgsStorage.Keys.EXERCISE_ID] as Int)
}
