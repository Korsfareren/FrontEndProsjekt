package com.example.frontendprosjekt

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.example.frontendprosjekt.viewmodel.EventFragmentViewModel
import java.util.*


class EventInfoFragment : Fragment() {

    private val viewModel: EventFragmentViewModel by activityViewModels()

    lateinit var eventImage: ImageView
    lateinit var eventHeader: TextView
    lateinit var eventArtist: TextView
    lateinit var eventDate: TextView
    lateinit var eventLocation: TextView
    lateinit var eventDetailedInfo: TextView
    lateinit var eventSignupBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventImage = view.findViewById(R.id.event_image_about)
        eventHeader = view.findViewById(R.id.header_event_about)
        eventArtist = view.findViewById(R.id.artist_event_about)
        eventDate = view.findViewById(R.id.date_event_about)
        eventLocation = view.findViewById(R.id.location_event_about)
        eventDetailedInfo = view.findViewById(R.id.detailed_info_event_about)
        eventSignupBtn = view.findViewById(R.id.signup_btn)


        registerOnClick()
    }

    private fun registerOnClick() {
        eventSignupBtn.setOnClickListener {
            requireActivity().supportFragmentManager.commit {
                add<RegisterFragment>(R.id.fragment_containter)
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }
    }
}