package com.noahjutz.gymroutines.ui.exercises

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.noahjutz.gymroutines.data.Repository
import com.noahjutz.gymroutines.data.domain.Exercise

class ExercisesViewModel @ViewModelInject constructor(private val repository: Repository) :
    ViewModel() {
    val exercises: LiveData<List<Exercise>>
        get() = repository.exercises
}
