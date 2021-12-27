package com.ziyiz.a7minutesworkout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import com.ziyiz.a7minutesworkout.databinding.ActivityFinishBinding
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class FinishActivity : AppCompatActivity() {

    private var binding: ActivityFinishBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        // set actionadd back
        setSupportActionBar(binding?.toolbarFinishActivity)
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        binding?.toolbarFinishActivity?.setNavigationOnClickListener {
            onBackPressed()
        }

        binding?.btnFinish?.setOnClickListener {
            // close the current FinishActivity
            finish()
        }

        // set up Dao
        val historyDao = (application as WorkoutApp).db.historyDao()
        addDateToDatabase(historyDao)
    }

    private fun addDateToDatabase(historyDao: HistoryDao) {
        val calendar = Calendar.getInstance()
        val dateTime = calendar.time
        Log.i("Date: ", " $dateTime")

        val sdf = SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault())
        val date = sdf.format(dateTime)
        Log.i("Formatted Date: ", " $date")

        lifecycleScope.launch {
            historyDao.insert(HistoryEntity((date)))
        }
    }
}