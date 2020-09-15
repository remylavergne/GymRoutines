package com.noahjutz.gymroutines.ui.routines.edit

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.noahjutz.gymroutines.data.Repository
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

    fun setName(name: String) {
        fullRoutine = fullRoutine.apply { routine.name = name }
    }
}
