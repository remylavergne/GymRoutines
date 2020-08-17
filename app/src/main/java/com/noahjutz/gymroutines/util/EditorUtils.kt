package com.noahjutz.gymroutines.util

import com.noahjutz.gymroutines.data.Repository
import com.noahjutz.gymroutines.data.domain.Exercise
import com.noahjutz.gymroutines.data.domain.FullRoutine
import com.noahjutz.gymroutines.data.domain.Routine

fun Repository.provideRoutine(routineId: Int): FullRoutine = getFullRoutine(routineId)
    ?: getFullRoutine(insert(FullRoutine(Routine(), emptyList())).toInt())
    ?: throw NullPointerException("Impossible exception")

fun Repository.provideExercise(exerciseId: Int): Exercise = getExercise(exerciseId)
    ?: getExercise(insert(Exercise()).toInt())
    ?: throw java.lang.NullPointerException("Impossible exception")
