package com.noahjutz.gymroutines.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.noahjutz.gymroutines.data.Repository
import com.noahjutz.gymroutines.data.domain.FullRoutine
import com.noahjutz.gymroutines.util.ArgsStorage

class EditRoutineViewModel @ViewModelInject constructor(private val repository: Repository) :
    ViewModel() {
    val routine: FullRoutine
        get() = repository.getFullRoutine(ArgsStorage.args["routineId"] as Int)!!
}