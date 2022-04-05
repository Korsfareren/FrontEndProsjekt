package com.example.frontendprosjekt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.frontendprosjekt.viewmodel.EventFragmentViewModel
import java.util.*

class EventFragment : Fragment() {

    private val viewModel: EventFragmentViewModel by activityViewModels()

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
        adapter = EventAdapter(
            listOf(
                EventData(
                    image = R.drawable.ic_oivind,
                    date = Date(),
                    header = "Oss som liker øl",
                    artist = "Staysman",
                    favourite = false, aboutEvent = "hei"
                ),
                EventData(
                    R.drawable.ic_oivind,
                    Date(),
                    "Lær C# på 10 minutter",
                    "Elon Musk",
                    aboutEvent = "hei",
                    false,
                )
            )
        ) { eventData ->
            viewModel.eventData = eventData
            requireActivity().supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<EventInfoFragment>(R.id.fragment_containter)
                addToBackStack(null)
            }
        }

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

    }


}