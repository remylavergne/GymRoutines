package com.noahjutz.gymroutines.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.noahjutz.gymroutines.data.Repository
import com.noahjutz.gymroutines.data.domain.Exercise
import com.noahjutz.gymroutines.data.domain.FullRoutine

class MainViewModel @ViewModelInject constructor(private val repository: Repository) :
    ViewModel() {
    val routines: LiveData<List<FullRoutine>>
        get() = repository.fullRoutines

    val exercises: LiveData<List<Exercise>>
        get() = repository.exercises
}