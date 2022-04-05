package com.example.frontendprosjekt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class EventFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: EventAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recycler_view)

        layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)


        //Send inn data fra API her
        adapter = EventAdapter(listOf(EventData(R.drawable.ic_oivind, Date(), "Oss som liker øl", "Staysman", false )))

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter


    }
}