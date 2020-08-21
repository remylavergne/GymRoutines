package com.noahjutz.gymroutines.util

/**
 * Stores arguments for passing between screens and viewmodels. Likely to be replaced with navigation component, when Jetpack Compose gets one.
 */
object ArgsStorage {
    enum class Keys { ROUTINE_ID, EXERCISE_ID }

    val args = HashMap<Keys, Any>()
}
