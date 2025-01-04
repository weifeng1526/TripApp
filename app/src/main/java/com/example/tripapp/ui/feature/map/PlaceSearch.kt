package com.example.tripapp.ui.feature.map

import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.OpeningHours
import com.google.android.libraries.places.api.model.Place

data class PlaceSearch(

    val id:String,
    val displayName:String,
    val formattedAddress:String,


)

data class SelectPlace(

    val id:String,
    val displayName:String,
    val formattedAddress:String,
    val location:LatLng?,
    val type:String,
)

