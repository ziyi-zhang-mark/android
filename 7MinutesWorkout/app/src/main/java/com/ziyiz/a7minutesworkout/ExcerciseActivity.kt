package com.ziyiz.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Toast
import com.ziyiz.a7minutesworkout.databinding.ActivityExcerciseBinding

class ExcerciseActivity : AppCompatActivity() {
    private var binding: ActivityExcerciseBinding? = null
    private var restTimer: CountDownTimer? = null
    private var restProgress = 0

    private var excerciseTimer: CountDownTimer? = null
    private var excerciseProgress = 0

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

        setupRestView()
    }

    override fun onDestroy() {
        super.onDestroy()

        if (restTimer != null) {
            restTimer?.cancel()
            restProgress = 0
        }
        if (excerciseTimer != null) {
            excerciseTimer?.cancel()
            excerciseProgress = 0
        }
        binding = null
    }

    private fun setupRestView() {
        if (restTimer != null) {
            restTimer?.cancel()
            restProgress = 0
        }
        setRestProgressBar()
    }

    private fun setupExcerciseView() {
        binding?.flProgressBar?.visibility = View.INVISIBLE
        binding?.tvTitle?.text = "Excercise name"
        binding?.flExcerciseView?.visibility = View.VISIBLE
        if (excerciseTimer != null) {
            excerciseTimer?.cancel()
            excerciseProgress = 0
        }
        setExcerciseProgressBar()
    }

    private fun setRestProgressBar() {
        binding?.progressBar?.progress = restProgress
        restTimer = object: CountDownTimer(10000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                restProgress++
                binding?.progressBar?.progress = 10 - restProgress
                binding?.tvTimer?.text = (10 - restProgress).toString()
            }

            override fun onFinish() {
                setupExcerciseView()
            }
        }.start()
    }

    private fun setExcerciseProgressBar() {
        binding?.progressBar?.progress = excerciseProgress
        excerciseTimer = object: CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                excerciseProgress++
                binding?.progressBarExcercise?.progress = 30 - excerciseProgress
                binding?.tvTimerExcercise?.text = (30 - excerciseProgress).toString()
            }

            override fun onFinish() {
                Toast.makeText(this@ExcerciseActivity, "30 seconds are over, let's go to the rest view", Toast.LENGTH_SHORT).show()
            }
        }.start()
    }
}