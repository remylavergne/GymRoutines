package com.noahjutz.gymroutines.ui.routines.edit

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.noahjutz.gymroutines.data.Repository
import com.noahjutz.gymroutines.data.domain.FullRoutine
import com.noahjutz.gymroutines.util.ArgsStorage

class EditRoutineViewModel @ViewModelInject constructor(private val repository: Repository) :
    ViewModel() {
    val routine: FullRoutine
        get() = repository.getFullRoutine(ArgsStorage.args[ArgsStorage.Keys.ROUTINE_ID] as Int)!!
}