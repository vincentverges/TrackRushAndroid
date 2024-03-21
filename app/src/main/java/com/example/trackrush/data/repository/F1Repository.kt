package com.example.trackrush.data.repository

import com.example.trackrush.data.api.F1ApiService
import com.example.trackrush.data.api.RetrofitInstance
import com.example.trackrush.data.model.Driver
import com.example.trackrush.data.model.Meeting
import com.example.trackrush.data.model.Session
import java.lang.Exception

class F1Repository (){

    private val apiService = RetrofitInstance.api
    suspend fun getAllMeetings(): List<Meeting>? {
        val response = RetrofitInstance.api.getAllMeetings()
        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }

    suspend fun getDriverForMeeting(circuitKey: Int, meetingKey: Int): List<Driver>? {
        val sessionResponse = apiService.getSessions(circuitKey = circuitKey,meetingKey = meetingKey, year = 2023, sessionName = "Race" )
        if (!sessionResponse.isSuccessful || sessionResponse.body().isNullOrEmpty()) {
            throw Exception("Failed to fetch sessions: ${sessionResponse.message()}")
        }

        val sessionKey = sessionResponse.body()!![0].sessionKey

        val driversResponse = apiService.getDrivers(meetingKey, sessionKey)
        if (driversResponse.isSuccessful && driversResponse.body() != null) {
            return driversResponse.body()
        } else {
            throw Exception("Failed to fetch drivers: ${driversResponse.message()}")
        }
    }
}