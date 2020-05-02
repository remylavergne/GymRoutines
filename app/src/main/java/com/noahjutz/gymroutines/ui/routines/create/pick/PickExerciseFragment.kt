package com.noahjutz.gymroutines.ui.routines.create.pick

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.card.MaterialCardView
import com.google.gson.Gson
import com.noahjutz.gymroutines.util.InjectorUtils
import com.noahjutz.gymroutines.R
import com.noahjutz.gymroutines.data.Exercise
import com.noahjutz.gymroutines.databinding.FragmentPickExerciseBinding
import com.noahjutz.gymroutines.ui.exercises.ExercisesViewModel
import com.noahjutz.gymroutines.ui.routines.create.MarginItemDecoration
import kotlinx.android.synthetic.main.fragment_pick_exercise.*

@Suppress("unused")
private const val TAG = "PickExerciseFragment"

class PickExerciseFragment : Fragment() {

    private val exercisesViewModel: ExercisesViewModel by viewModels { viewModelFactory }
    private val pickExerciseViewModel: PickExerciseViewModel by viewModels { viewModelFactory }
    private val viewModelFactory by lazy { InjectorUtils.provideViewModelFactory(requireActivity().application) }
    private val args: PickExerciseFragmentArgs by navArgs()
    private lateinit var adapter: ExercisesAdapter
    private lateinit var binding: FragmentPickExerciseBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_pick_exercise, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initBinding()
        initRecyclerView()
        initActivity()
        initViewModel()
    }

    private fun initBinding() {
        binding.fragment = this
    }

    private fun initViewModel() {
        exercisesViewModel.exercises.observe(viewLifecycleOwner, Observer { exercises ->
            adapter.submitList(exercises)
        })
        pickExerciseViewModel.exercises.observe(viewLifecycleOwner, Observer { exercises ->
            fab_pick_exercises.visibility =
                if (exercises.isEmpty()) GONE
                else VISIBLE
        })
    }

    private fun initActivity() {
        requireActivity().apply {
            title = "Pick Exercise"
        }
    }

    private fun initRecyclerView() {
        val onItemClickListener = object : ExercisesAdapter.OnItemClickListener {
            override fun onExerciseClick(exercise: Exercise, card: MaterialCardView) {
                card.isChecked = (!card.isChecked)
                if (card.isChecked) pickExerciseViewModel.addExercise(exercise)
                else pickExerciseViewModel.removeExercise(exercise)
            }

            override fun onExerciseLongClick(exercise: Exercise, card: MaterialCardView) {}
        }

        adapter = ExercisesAdapter(onItemClickListener)

        recycler_view.apply {
            adapter = this@PickExerciseFragment.adapter
            layoutManager = LinearLayoutManager(this@PickExerciseFragment.requireContext())
            setHasFixedSize(true)
            addItemDecoration(
                MarginItemDecoration(
                    resources.getDimension(R.dimen.any_margin_default).toInt()
                )
            )
        }
    }

    fun pickExercise() {
        val gson = Gson()
        val listString = gson.toJson(pickExerciseViewModel.exercises.value)
        val action = PickExerciseFragmentDirections.addExercise(args.routineId, listString)
        findNavController().navigate(action)
    }
}