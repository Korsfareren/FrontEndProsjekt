package com.example.frontendprosjekt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.frontendprosjekt.apiobjects.EventData

class EventAdapter(
    private var dataSet: List<EventData> = listOf(), val callback: (EventData) -> Unit
) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    inner class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var constraintLayout: ConstraintLayout = view.findViewById(R.id.cell_view_constraint_layout)
        var imageEvent: ImageView = view.findViewById(R.id.image_event)
        var dateEvent: TextView = view.findViewById(R.id.date_event)
        var city: TextView = view.findViewById(R.id.city_event)
        var headerEvent: TextView = view.findViewById(R.id.header_event)
        var artistNameEvent: TextView = view.findViewById(R.id.artist_name_event)
        var participantsEvent: TextView = view.findViewById(R.id.participants_event)
        var favoriteEventBtn: AppCompatImageButton = view.findViewById(R.id.favourite_event_btn)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): EventViewHolder {
        val cellView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.event_main_page_cell_item, viewGroup, false)

        // Setter størrelsen på cellen
        val params: ViewGroup.LayoutParams = cellView.layoutParams
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        cellView.layoutParams = params

        return EventViewHolder(cellView)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val eventObject = dataSet[position]

        //TODO Dette har jeg gjort
        Glide
            .with(holder.itemView)
            .load(eventObject.image)
            .override(200, 200)
            .into(holder.imageEvent)

        holder.city.text = eventObject.city.name
        holder.dateEvent.text = eventObject.dateFrom
        holder.headerEvent.text = eventObject.header
        holder.artistNameEvent.text = eventObject.host.name
        holder.participantsEvent.text = eventObject.participants.toString()
        holder.constraintLayout.setOnClickListener {
            callback(eventObject)
        }
    }

    override fun getItemCount() = dataSet.size

    fun updateDataSet(newDataSet: List<EventData>) {
        dataSet = newDataSet
        notifyDataSetChanged()
    }
}