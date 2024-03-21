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

        val dummyDrivers = listOf(
            Driver(
                broadcastName = "Lewis Hamilton",
                countryCode = "GB",
                driverNumber = 44,
                firstName = "Lewis",
                fullName = "Lewis Carl Davidson Hamilton",
                headshotUrl = "", // URL factice ou réelle de la photo du pilote
                lastName = "Hamilton",
                meetingKey = 1, // Clé factice de la réunion, ajustez selon vos besoins
                nameAcronym = "HAM",
                sessionKey = 1, // Clé factice de la session, ajustez selon vos besoins
                teamColour = "#00D2BE", // Couleur factice de l'équipe Mercedes
                teamName = "Mercedes"
            ),
            Driver(
                broadcastName = "Max Verstappen",
                countryCode = "NL",
                driverNumber = 33,
                firstName = "Max",
                fullName = "Max Emilian Verstappen",
                headshotUrl = "", // URL factice ou réelle de la photo du pilote
                lastName = "Verstappen",
                meetingKey = 1, // Clé factice de la réunion, ajustez selon vos besoins
                nameAcronym = "VER",
                sessionKey = 1, // Clé factice de la session, ajustez selon vos besoins
                teamColour = "#0600EF", // Couleur factice de l'équipe Red Bull Racing
                teamName = "Red Bull Racing"
            ),
            Driver(
                broadcastName = "Charles Leclerc",
                countryCode = "MC",
                driverNumber = 16,
                firstName = "Charles",
                fullName = "Charles Marc Hervé Percival Leclerc",
                headshotUrl = "", // URL factice ou réelle de la photo du pilote
                lastName = "Leclerc",
                meetingKey = 1, // Clé factice de la réunion, ajustez selon vos besoins
                nameAcronym = "LEC",
                sessionKey = 1, // Clé factice de la session, ajustez selon vos besoins
                teamColour = "#DC0000", // Couleur factice de l'équipe Ferrari
                teamName = "Ferrari"
            )
            // Ajoutez d'autres pilotes si nécessaire
        )

        val binding = FragmentDriverListBinding.bind(view)
        binding.list.layoutManager = LinearLayoutManager(context)
        binding.list.adapter = MyListRecyclerViewAdapter(dummyDrivers)
    }

    companion object {
        const val ARG_COLUMN_COUNT = "column-count"
    }
}