package com.example.trackrush.ui.drivers

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.trackrush.R
import com.example.trackrush.data.model.Driver
import com.example.trackrush.databinding.FragmentDriverListItemBinding


class MyListRecyclerViewAdapter(
    private val drivers: List<Driver>
) : RecyclerView.Adapter<MyListRecyclerViewAdapter.DriverViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DriverViewHolder {
        val binding = FragmentDriverListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DriverViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DriverViewHolder, position: Int) {
        val driver = drivers[position]
        holder.bind(driver)
    }

    override fun getItemCount(): Int = drivers.size

    inner class DriverViewHolder(private val binding: FragmentDriverListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(driver: Driver) {
            binding.driverName.text = driver.broadcastName
            binding.teamName.text = driver.teamName
        }
    }
}