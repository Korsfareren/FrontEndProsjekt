package com.example.frontendprosjekt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class EventAdapter(private val dataSet: List<EventData>
) : RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

    inner class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val constraintLayout: ConstraintLayout = view.findViewById(R.id.cell_view_constraint_layout)
        var imageEvent: ImageView = view.findViewById(R.id.image_event)
        val dateEvent: TextView = view.findViewById(R.id.date_event)
        val headerEvent: TextView = view.findViewById(R.id.header_event)
        val artistNameEvent: TextView = view.findViewById(R.id.artist_name_event)
        val favoriteEventBtn: AppCompatImageButton = view.findViewById(R.id.favourite_event_btn)
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): EventViewHolder {
        // Create a new view, which defines the UI of the list item
        val cellView = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.event_main_page_cell_item, viewGroup, false)

        // Setter størrelsen på cellen
        val params: ViewGroup.LayoutParams = cellView.layoutParams
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        cellView.layoutParams = params

        return EventViewHolder(cellView)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val image = dataSet[position].image
        val date = dataSet[position].date
        val header = dataSet[position].header
        val artist = dataSet[position].artist
        val favourite = dataSet[position].favourite

        holder.imageEvent = image
        holder.dateEvent.text = date.toString()
        holder.headerEvent.text = header
        holder.artistNameEvent.text = artist

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}