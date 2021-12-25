package com.ziyiz.a7minutesworkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.FrameLayout
import android.widget.Toast
import com.ziyiz.a7minutesworkout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("MainActivity","MainActivity onCreate() called")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        binding?.flStart?.setOnClickListener {
            Toast.makeText(this, "start", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ExerciseActivity::class.java)
            startActivity(intent)
        }

        binding?.flBMI?.setOnClickListener {
            val intent = Intent(this, BMIActivity::class.java)
            startActivity(intent)
        }

        binding?.flHistory?.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("MainActivity","MainActivity onDestroy() called")
        binding = null
    }
}