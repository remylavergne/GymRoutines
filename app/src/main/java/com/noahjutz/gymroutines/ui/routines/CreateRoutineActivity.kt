package com.noahjutz.gymroutines.ui.routines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.noahjutz.gymroutines.R
import kotlinx.android.synthetic.main.activity_create_routine.*

class CreateRoutineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_routine)

        initViews()

        title = "Create Routine"
    }

    private fun initViews() {
        fab_save_routine.setOnClickListener { saveRoutine() }

        // TODO: Populate Views
    }

    private fun saveRoutine() {
        // TODO: Save to Database
        finish()
    }
}
