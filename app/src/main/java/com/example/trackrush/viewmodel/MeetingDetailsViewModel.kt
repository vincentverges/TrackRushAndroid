package com.example.trackrush.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.trackrush.data.repository.F1Repository

class MeetingDetailsViewModel: ViewModel() {
    private val repository = F1Repository()

    fun getMeetingDetails(meetingKey: Int) = liveData {
        val retrievedMeeting = repository.getSessionsForMeeting(meetingKey)
        emit(retrievedMeeting)
    }
}