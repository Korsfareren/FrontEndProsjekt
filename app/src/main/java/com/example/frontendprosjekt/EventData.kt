package com.example.frontendprosjekt


import android.graphics.drawable.Drawable
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import java.util.*

class EventData(
    val image: Int,
    val date: Date,
    val header: String,
    val artist: String,
    val favourite: Boolean
)