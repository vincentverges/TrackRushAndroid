package com.example.trackrush.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Meeting(
    @SerializedName("circuit_key") val circuitKey: Int,
    @SerializedName("circuit_short_name") val circuitShortName: String,
    @SerializedName("country_code") val countryCode: String,
    @SerializedName("country_key") val countryKey: Int,
    @SerializedName("country_name") val countryName: String,
    @SerializedName("date_start") val dateStart: String,
    @SerializedName("gmt_offset") val gmtOffset: String,
    @SerializedName("location") val location: String,
    @SerializedName("meeting_key") val meetingKey: Int,
    @SerializedName("meeting_name") val meetingName: String,
    @SerializedName("meeting_official_name") val meetingOfficialName: String,
    @SerializedName("year") val year: Int
) : Serializable
