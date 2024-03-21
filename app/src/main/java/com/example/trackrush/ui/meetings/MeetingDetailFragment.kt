package com.example.trackrush.ui.meetings

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.trackrush.R
import com.example.trackrush.data.api.F1ApiService
import com.example.trackrush.data.model.Meeting
import com.example.trackrush.data.repository.F1Repository
import com.example.trackrush.databinding.FragmentMeetingDetailBinding
import com.example.trackrush.ui.drivers.DriverListFragment
import com.google.gson.Gson
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class MeetingDetailFragment : Fragment() {

    private var _binding: FragmentMeetingDetailBinding? = null
    private val binding get() = _binding!!
    private val f1Repository = F1Repository()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMeetingDetailBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    @SuppressLint("SetTextI18n", "DiscouragedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val meeting = arguments?.getSerializable("meetingDetails") as Meeting

        val circuitKey = meeting.circuitKey
        val meetingKey = meeting.meetingKey


        with(binding) {
            meetingNameTextView.text = meeting.meetingName
            meetingOfficialNameTextView.text= meeting.meetingOfficialName
            dateStartTextView.text= "\uD83D\uDCC5 ${formatDate(meeting.dateStart)}"
            circuitShortName.text= "\uD83C\uDFCE Circuit: ${meeting.circuitShortName}"

            val circuitImageResId = resources.getIdentifier("c${meeting.circuitKey}", "drawable", context?.packageName)
            circuitImageView.setImageResource(circuitImageResId)

            showDriversButton.setOnClickListener {

                viewLifecycleOwner.lifecycleScope.launch {
                    try {
                        val drivers = f1Repository.getDriverForMeeting(circuitKey,meetingKey)

                        val driversJson = Gson().toJson(drivers)
                        val bundle = Bundle().apply {
                            putString("drivers", driversJson)
                        }
                        findNavController().navigate(R.id.action_meetingDetailsFragment_to_driverListFragment,bundle)
                    } catch (e: Exception) {

                    }
                }
            }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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