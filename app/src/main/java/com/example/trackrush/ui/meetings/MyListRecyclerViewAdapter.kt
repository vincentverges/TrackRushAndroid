package com.example.trackrush.ui.meetings

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.trackrush.data.model.Meeting
import com.example.trackrush.databinding.FragmentMeetingListItemBinding
import java.text.SimpleDateFormat
import java.util.Locale

class MyListRecyclerViewAdapter(
    private val meetings: List<Meeting>?
) : RecyclerView.Adapter<MyListRecyclerViewAdapter.MeetingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeetingViewHolder {
        val binding = FragmentMeetingListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MeetingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MeetingViewHolder, position: Int) {
        meetings?.get(position)?.let { meeting ->
            holder.bind(meeting)
        }
    }

    override fun getItemCount(): Int = meetings?.size ?: 0

    class MeetingViewHolder(private val binding: FragmentMeetingListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(meeting: Meeting) {
            with(binding) {
                meetingOfficialNameTextView.text = meeting.meetingOfficialName
                meetingNameTextView.text = meeting.meetingName
                locationTextView.text = "${meeting.location}, ${meeting.countryName}"
                dateStartTextView.text = "Date: ${formatDate(meeting.dateStart)}"
            }
        }

        private fun formatDate(dateString: String): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())

            return try {
                val date = inputFormat.parse(dateString)
                outputFormat.format(date!!)
            } catch (e: Exception){
                "Invalid Date"
            }
        }
    }

}