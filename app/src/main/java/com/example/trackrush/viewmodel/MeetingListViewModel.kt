package com.example.trackrush.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.trackrush.data.repository.F1Repository

class MeetingListViewModel : ViewModel() {
    private val repository = F1Repository()

    val meetings = liveData {
        val retrievedMeetings = repository.getAllMeetings()
            emit(retrievedMeetings)
    }
}