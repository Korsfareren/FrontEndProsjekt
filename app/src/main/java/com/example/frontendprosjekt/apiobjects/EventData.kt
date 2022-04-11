package com.example.frontendprosjekt.apiobjects

import com.example.frontendprosjekt.apiobjects.CityName
import com.example.frontendprosjekt.apiobjects.HostName

class EventData(
    val image: String,
    val dateFrom: String,
    val city: CityName,
    val header: String,
    val host: HostName,
    val participants: Int
)