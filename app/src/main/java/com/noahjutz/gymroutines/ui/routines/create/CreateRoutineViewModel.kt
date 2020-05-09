package com.noahjutz.gymroutines.ui.routines.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.noahjutz.gymroutines.data.*
import kotlinx.coroutines.runBlocking

@Suppress("unused")
private const val TAG = "CreateRoutineViewModel"

class CreateRoutineViewModel(
    private val repository: Repository,
    private var routineId: Int
) : ViewModel() {
    /**
     * The [Rwe] object that is being created/edited
     * @see initRwe
     * @see save
     */
    private val _rwe = MediatorLiveData<Rwe>()
    val rwe: LiveData<Rwe>
        get() = _rwe

    /**
     * Data binding fields
     * [MediatorLiveData] Sources for [rwe]
     * @see initBinding
     */
    val name = MutableLiveData<String>()
    val description = MutableLiveData<String>()

    private val _exercises = MutableLiveData<ArrayList<ExerciseWrapper>>()

    init {
        initRwe()
        initBinding()
    }

    private fun initBinding() {
        name.value = rwe.value!!.routine.name
        description.value = rwe.value!!.routine.description
    }

    /**
     * Initializes [Rwe] Object
     * Adds [name] and [description] as source
     */
    private fun initRwe() {
        _rwe.run {
            value = getRweById(routineId)
                ?: Rwe(
                    Routine(""),
                    listOf()
                )

            _exercises.value = value?.exerciseWrappers as? ArrayList<ExerciseWrapper> ?: ArrayList()

            addSource(name) { name ->
                _rwe.value = _rwe.value!!.apply {
                    routine.name = name.trim()
                }
            }

            addSource(description) { description ->
                _rwe.value = _rwe.value!!.apply {
                    routine.description = description.trim()
                }
            }

            addSource(_exercises) { exercises ->
                _rwe.value = Rwe(
                    _rwe.value!!.routine,
                    exercises
                )
            }
        }
    }

    /**
     * Auto-save
     */
    override fun onCleared() {
        super.onCleared()
        save()
    }

    /**
     * Inserts the [rwe]'s [Routine].
     * @see insert
     * Assigns the [rwe]'s [Exercise] list to routines with cross references.
     * @see assignExercisesToRoutine
     */
    private fun save() {
        val routine = rwe.value!!.routine
        val routineId = insert(routine).toInt()
        val exerciseIds = rwe.value!!.exerciseWrappers.map { it.exerciseId }
        assignExercisesToRoutine(routineId, exerciseIds)
    }

    /**
     * [repository] access functions
     */

    private fun insert(routine: Routine): Long = runBlocking {
        repository.insert(routine)
    }

    private fun getRweById(routineId: Int): Rwe? = runBlocking {
        repository.getRweById(routineId)
    }

    private fun assignExercisesToRoutine(routineId: Int, exerciseIds: List<Int>) = runBlocking {
        repository.assignExercisesToRoutine(routineId, exerciseIds)
    }

    private fun unassignExercisesFromRoutine(routineId: Int) = runBlocking {
        repository.unassignExercisesFromRoutine(routineId)
    }

    /**
     * Functions for [CreateRoutineFragment]
     */
    fun addExercises(exerciseWrappers: List<ExerciseWrapper>) {
        for (e in exerciseWrappers) addExercise(e)
    }

    fun removeExercise(exerciseWrapper: ExerciseWrapper) {
        if (exerciseWrapper in _exercises.value!!)
            _exercises.value = _exercises.value!!.apply { remove(exerciseWrapper) }
    }

    fun addExercise(exerciseWrapper: ExerciseWrapper) {
        if (exerciseWrapper !in _exercises.value!!)
            _exercises.value = _exercises.value!!.apply { add(exerciseWrapper) }
    }
}
