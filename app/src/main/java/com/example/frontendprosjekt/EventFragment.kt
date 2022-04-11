package com.example.frontendprosjekt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.frontendprosjekt.apiobjects.CityName
import com.example.frontendprosjekt.apiobjects.EventData
import com.example.frontendprosjekt.apiobjects.HostName
import com.example.frontendprosjekt.viewmodel.EventFragmentViewModel

class EventFragment : Fragment() {

    private val viewModel: EventFragmentViewModel by activityViewModels()

    lateinit var layoutManager: LinearLayoutManager
    lateinit var recyclerView: RecyclerView
    lateinit var spinner: Spinner
    lateinit var adapter: EventAdapter
    lateinit var queue: RequestQueue

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        queue = Volley.newRequestQueue(activity)
        citiesAdapter(view)

        getEventApiData("")

        recyclerView = view.findViewById(R.id.recycler_view)
        layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        //Send inn data fra API her
        //TODO Har sendt inn masse tom data som placeholder
        adapter = EventAdapter(
            listOf(
                EventData("", "", CityName(""), "", HostName(""), 0),

                )
        ) { eventData ->
            viewModel.eventData = eventData

            requireActivity().supportFragmentManager.commit {
                setReorderingAllowed(true)
                replace<EventInfoFragment>(R.id.fragment_containter)
                addToBackStack(null)
            }
        }
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }

    //TODO Spør Øivind om hvordan sende by inn i spinner
    private fun citiesAdapter(view: View) {
        spinner = view.findViewById(R.id.event_location_selector_spinner)

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.cities,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        spinner.onItemSelectedListener = object: AdapterView.OnItemClickListener,
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                getEventApiData(parent?.getItemAtPosition(position).toString())
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            }
        }

    }

    fun getEventApiData(selectedCity: String) {
        viewModel.getEventApiData(queue) { eventDataObject ->
            //TODO Dette ble gjort, man måtte oppdatere dataSet i adapter for at recyclerView skulle vise API-data
            if (eventDataObject == null) {
                Toast.makeText(activity, "Teknisk feil", Toast.LENGTH_SHORT).show()
            } else {
                val sortedList = mutableListOf<EventData>()

                //TODO
                for (dataObject in eventDataObject.result) {
                    if (dataObject.city.name == selectedCity || selectedCity == "") {
                        sortedList.add(dataObject)
                    }
                }
                adapter.updateDataSet(sortedList)
            }
        }
    }
}