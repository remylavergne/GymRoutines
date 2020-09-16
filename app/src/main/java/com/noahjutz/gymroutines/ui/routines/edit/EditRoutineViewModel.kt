package com.noahjutz.gymroutines.ui.routines.edit

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.noahjutz.gymroutines.data.Repository
import com.noahjutz.gymroutines.data.domain.Exercise
import com.noahjutz.gymroutines.data.domain.ExerciseImpl
import com.noahjutz.gymroutines.data.domain.FullRoutine
import com.noahjutz.gymroutines.util.ArgsStorage
import com.noahjutz.gymroutines.util.provideRoutine

class EditRoutineViewModel @ViewModelInject constructor(private val repository: Repository) :
    ViewModel() {
    var fullRoutine: FullRoutine
        get() = repository.provideRoutine(ArgsStorage.routineId)
        set(fullRoutine) {
            repository.insert(fullRoutine)
        }

    val exercises: LiveData<List<Exercise>>
        get() = repository.exercises

    fun setName(name: String) {
        fullRoutine = fullRoutine.apply { routine.name = name }
    }

    fun setDescription(description: String) {
        fullRoutine = fullRoutine.apply { routine.description = description }
    }

    fun addExercise(exercise: ExerciseImpl) {
        updateExercises {
            add(exercise)
        }
    }

    private fun updateExercises(action: MutableList<ExerciseImpl>.() -> Unit) {
        fullRoutine = fullRoutine.apply {
            exercises = (exercises as? ArrayList ?: mutableListOf()).apply {
                action()
            }.toList()
        }
    }
}
