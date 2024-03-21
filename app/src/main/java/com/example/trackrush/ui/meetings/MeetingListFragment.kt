package com.example.trackrush.ui.meetings

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.example.trackrush.databinding.FragmentMeetingListBinding
import com.example.trackrush.viewmodel.MeetingListViewModel

/**
 * A fragment representing a list of Items.
 */
class MeetingListFragment : Fragment() {

    private var _binding: FragmentMeetingListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MeetingListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMeetingListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Formula 1 Championship"

        binding.meetingsRecyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.meetings.observe(viewLifecycleOwner) { meetingsList ->
            binding.meetingsRecyclerView.adapter = MyListRecyclerViewAdapter(meetingsList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}