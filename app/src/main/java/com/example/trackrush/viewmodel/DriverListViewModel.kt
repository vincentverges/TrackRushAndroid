package com.example.trackrush.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.trackrush.data.repository.F1Repository

class DriverListViewModel: ViewModel() {
    private val repository = F1Repository()

    fun getDriversForMeeting(circuitKey: Int, sessionKey: Int) = liveData {
        val retrievedDrivers = repository.getDriverForMeeting(circuitKey, sessionKey)
        emit(retrievedDrivers)
    }
}