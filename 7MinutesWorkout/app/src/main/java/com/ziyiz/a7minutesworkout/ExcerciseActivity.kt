package com.ziyiz.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ziyiz.a7minutesworkout.databinding.ActivityExcerciseBinding

class ExcerciseActivity : AppCompatActivity() {
    private var binding: ActivityExcerciseBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExcerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarExcercise)
        // add back button
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        
        binding?.toolbarExcercise?.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}