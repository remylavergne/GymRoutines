package com.noahjutz.gymroutines.ui.routines

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.noahjutz.gymroutines.data.Repository
import com.noahjutz.gymroutines.data.domain.FullRoutine

class RoutinesViewModel @ViewModelInject constructor(private val repository: Repository) :
    ViewModel() {
    val routines: LiveData<List<FullRoutine>>
        get() = repository.fullRoutines
}
