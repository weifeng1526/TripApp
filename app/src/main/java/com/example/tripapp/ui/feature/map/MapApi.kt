package com.example.tripapp.ui.feature.map

import retrofit2.http.Body
import retrofit2.http.POST

interface MapApi{
    @POST("map/place")
    suspend fun selectplace(
        
    )



}