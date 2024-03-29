package com.ziyiz.criminalintent

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*

private  const val TAG = "CrimeListViewModel"

class CrimeListViewModel: ViewModel() {
    private val crimeRepository = CrimeRepository.get()

    private val _crimes: MutableStateFlow<List<Crime>> = MutableStateFlow(emptyList())
    val crimes: StateFlow<List<Crime>>
        get() = _crimes.asStateFlow()

    init {
        Log.d(TAG, "init starting")
        viewModelScope.launch {
            Log.d(TAG, "coroutine launched")
            crimeRepository.getCrimes().collect {
                _crimes.value = it
            }
        }
        Log.d(TAG, "init ending")
    }

    suspend fun addCrime(crime: Crime) {
        crimeRepository.addCrime(crime)
    }
}