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


package com.noahjutz.gymroutines

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.focus.ExperimentalFocus
import androidx.compose.ui.input.key.ExperimentalKeyInput
import androidx.lifecycle.MutableLiveData
import androidx.ui.test.*
import com.noahjutz.gymroutines.data.domain.Set
import com.noahjutz.gymroutines.ui.MainActivity
import com.noahjutz.gymroutines.ui.routines.create.CreateRoutineEditor
import com.noahjutz.gymroutines.ui.routines.create.CreateRoutinePresenter
import com.noahjutz.gymroutines.ui.routines.create.CreateRoutineScreen
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalFoundationApi
@ExperimentalFocus
class CreateRoutineScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val presenter = mockk<CreateRoutinePresenter>(relaxed = true).apply {
        every { initialName } returns "Test Routine Name"
        every { sets } returns MutableLiveData(listOf(Set(-1), Set(-1), Set(-1)))
        every { getExerciseName(-1) } returns "Test Exercise Name"
    }
    private val editor = mockk<CreateRoutineEditor>(relaxed = true)
    private val onAddExercise: () -> Unit = mockk(relaxed = true)
    private val popBackStack: () -> Unit = mockk(relaxed = true)

    @Before
    fun initializeComopseTestRule() {
        composeTestRule.apply {
            setContent {
                MaterialTheme {
                    CreateRoutineScreen(
                        onAddExercise = onAddExercise,
                        popBackStack = popBackStack,
                        presenter = presenter,
                        editor = editor
                    )
                }
            }
        }
    }

    @ExperimentalKeyInput
    @ExperimentalFoundationApi
    @ExperimentalFocus
    @Test
    fun routineNameTextFieldWorks() {
        composeTestRule.apply {
            onNodeWithText("Test Routine Name").apply {
                performTextInput("Legs")
            }
            onNodeWithSubstring("Legs").assertExists()
        }
    }

    @Test
    fun addExerciseButtonCallsAddExercise() {
        composeTestRule.apply {
            onNodeWithTag("addExerciseFab").performClick()
            verify { onAddExercise() }
        }
    }

    @Test
    fun backButtonCallsPopBackStack() {
        composeTestRule.apply {
            onNodeWithTag("backButton").performClick()
            verify { popBackStack() }
        }
    }
}