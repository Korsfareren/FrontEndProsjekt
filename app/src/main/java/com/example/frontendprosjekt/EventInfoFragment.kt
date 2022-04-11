package com.example.frontendprosjekt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.*
import com.bumptech.glide.Glide
import com.example.frontendprosjekt.viewmodel.EventFragmentViewModel


class EventInfoFragment : Fragment() {

    private val viewModel: EventFragmentViewModel by activityViewModels()

    private lateinit var eventAboutEvent: TextView
    private lateinit var eventLocation: TextView
    private lateinit var eventSignupBtn: Button
    private lateinit var eventImage: ImageView
    private lateinit var eventHeader: TextView
    private lateinit var eventArtist: TextView
    private lateinit var eventDate: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews(view)

        //eventImage.setImageResource(viewModel.eventData.image)
        Glide
            .with(this)
            .load(viewModel.eventData.image)
            .override(200, 200)
            .into(eventImage)
        //eventAboutEvent.text = viewModel.eventData.aboutEvent
        eventLocation.text = viewModel.eventData.city.name
        eventHeader.text = viewModel.eventData.header
        eventArtist.text = viewModel.eventData.host.name
        eventDate.text = viewModel.eventData.dateFrom

        registerOnClick()
    }

    private fun registerOnClick() {
        eventSignupBtn.setOnClickListener {
            requireActivity().supportFragmentManager.commit {
                replace<RegisterFragment>(R.id.fragment_containter)
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }
    }

    private fun bindViews(view: View) {
        eventAboutEvent = view.findViewById(R.id.detailed_info_event_about)
        eventSignupBtn = view.findViewById(R.id.signup_btn)
        eventLocation = view.findViewById(R.id.location_event_about)
        eventHeader = view.findViewById(R.id.header_event_about)
        eventArtist = view.findViewById(R.id.artist_event_about)
        eventImage = view.findViewById(R.id.event_image_about)
        eventDate = view.findViewById(R.id.date_event_about)
    }
}