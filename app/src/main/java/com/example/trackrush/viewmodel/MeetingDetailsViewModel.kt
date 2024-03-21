package com.example.trackrush.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.trackrush.data.model.Driver
import com.example.trackrush.data.repository.F1Repository

class MeetingDetailsViewModel: ViewModel() {
    private val repository = F1Repository()

    fun getMeetingDetails(circuitKey: Int, meetingKey: Int) = liveData {
        try {
            val retrievedDriver: List<Driver>? = repository.getDriverForMeeting(circuitKey, meetingKey)
            emit(retrievedDriver)
        } catch (e: Exception) {
            emit(listOf<Driver>())
        }

    }
}