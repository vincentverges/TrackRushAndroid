package com.example.trackrush.ui.drivers

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.trackrush.R
import com.example.trackrush.data.model.Driver
import com.example.trackrush.databinding.FragmentDriverListBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DriverListFragment : Fragment() {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_driver_list, container, false)

        val drivers = listOf<Driver>()

        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MyListRecyclerViewAdapter(drivers)
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val driverJson = arguments?.getString("drivers")
        val driversType = object : TypeToken<List<Driver>>() {}.type
        val drivers: List<Driver> = Gson().fromJson(driverJson, driversType)

        setupRecyclerView(drivers, view)
    }

    private fun setupRecyclerView(drivers: List<Driver>, view: View) {
        val binding = FragmentDriverListBinding.bind(view)
        binding.list.layoutManager = LinearLayoutManager(context)
        binding.list.adapter = MyListRecyclerViewAdapter(drivers)
    }
    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
    }
}