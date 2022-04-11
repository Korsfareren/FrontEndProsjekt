package com.example.frontendprosjekt.apiobjects

class EventDataMoreInfo(
    val category: CategoryName,
    val city: CityName,
    val dateFrom: String,
    val dateTo: String,
    val description: String,
    val header: String,
    val host: HostName,
    val image: String,
    val location: LocationName,
    val price: Int
)