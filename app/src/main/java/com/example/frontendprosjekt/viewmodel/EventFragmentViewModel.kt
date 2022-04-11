package com.example.frontendprosjekt.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.beust.klaxon.Klaxon
import com.example.frontendprosjekt.apiobjects.EventData
import com.example.frontendprosjekt.ListEventData

class EventFragmentViewModel : ViewModel() {
    lateinit var eventData: EventData
    var currentEvent = listOf<EventData>()

    fun getEventApiData(requestQueue: RequestQueue, callback: (ListEventData?) -> Unit) {

        val url = "https://hbcgee7e.api.sanity.io/v2021-10-21/data/query/production?query=*%5B_type%20%3D%3D%20%24type%5D%20%7B%0A%20%20header%2C%20host-%3E%7Bname%7D%2C%20image%2C%20dateFrom%2C%20dateTo%2C%20price%2C%20city-%3E%7Bname%7D%2C%20location-%3E%7Blocation%7D%2C%20description%2C%20category-%3E%7Bname%7D%2C%20participants%0A%7D&%24type=%22event%22"

        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { json ->
                val listEventData = Klaxon().parse<ListEventData>(json)
                callback(listEventData)
                currentEvent = listEventData?.result ?: listOf()
            },
            { error ->
                Log.e("Error:", error.message ?: "")
                callback(null)
            }
        )
        requestQueue.add(stringRequest)
    }
}