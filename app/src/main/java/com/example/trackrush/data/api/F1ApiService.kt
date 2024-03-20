package com.example.trackrush.data.api

import com.example.trackrush.data.model.Driver
import com.example.trackrush.data.model.Meeting
import com.example.trackrush.data.model.Session
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface F1ApiService {
    @GET("meetings")
    suspend fun getAllMeetings(): Response<List<Meeting>>

    @GET("sessions")
    suspend fun getSessionsForMeeting(@Query("meeting_key") meetingKey: Int): Response<List<Session>>

    @GET("drivers")
    suspend fun getDriversForSession(@Query("session_key") sessionKey: Int): Response<List<Driver>>
}