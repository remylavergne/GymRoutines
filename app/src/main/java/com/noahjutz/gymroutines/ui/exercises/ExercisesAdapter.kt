/*
 * GymRoutines
 * Copyright (C) 2020  Noah Jutz
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.noahjutz.gymroutines.ui.exercises

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.noahjutz.gymroutines.R
import com.noahjutz.gymroutines.data.domain.Exercise
import com.noahjutz.gymroutines.util.DiffUtilCallback
import com.noahjutz.gymroutines.util.setTextOrHide
import com.noahjutz.gymroutines.util.setTextOrUnnamed
import kotlinx.android.synthetic.main.listitem_exercise.view.*

class ExercisesAdapter(
    private val exerciseListener: ExerciseListener
) : ListAdapter<Exercise, ExercisesAdapter.ExerciseHolder>(diffUtil) {
    fun getExerciseAt(pos: Int): Exercise = getItem(pos)

    inner class ExerciseHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ExerciseHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.listitem_exercise, parent, false)
    )

    override fun onBindViewHolder(holder: ExerciseHolder, position: Int) {
        val exercise = getItem(position)
        val (exerciseName, exerciseDesc) = exercise

        holder.itemView.apply {
            name.setTextOrUnnamed(exerciseName)
            description.setTextOrHide(exerciseDesc)

            setOnClickListener { exerciseListener.onExerciseClick(exercise) }
        }
    }

    interface ExerciseListener {
        fun onExerciseClick(exercise: Exercise)
    }
}

private val diffUtil =
    DiffUtilCallback<Exercise>({ old, new -> old.exerciseId == new.exerciseId })
