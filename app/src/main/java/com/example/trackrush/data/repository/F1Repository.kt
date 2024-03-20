package com.example.trackrush.data.repository

import com.example.trackrush.data.api.RetrofitInstance
import com.example.trackrush.data.model.Driver
import com.example.trackrush.data.model.Meeting
import com.example.trackrush.data.model.Session

class F1Repository {
    suspend fun getAllMeetings(): List<Meeting>? {
        val response = RetrofitInstance.api.getAllMeetings()
        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }

    suspend fun getSessionsForMeeting(meetingKey: Int): List<Session>? {
        val response = RetrofitInstance.api.getSessionsForMeeting(meetingKey)
        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }

    suspend fun getDriversForSession(sessionKey: Int): List<Driver>? {
        val response = RetrofitInstance.api.getDriversForSession(sessionKey)
        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }
}