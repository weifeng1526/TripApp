package com.example.tripapp.ui.feature.map

import com.example.tripapp.ui.restful.Poi
import com.ron.restdemo.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface MapApi{
    @POST("map/place")
    suspend fun selectPlace(@Body placeDetail:SelectPlaceDetail):List<SelectPlaceDetail>
}
object RetrofitInstance {
    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/TripAppEnd/") // Base URL
            .addConverterFactory(GsonConverterFactory.create()) // GSON for JSON conversion
            .build()
            .create(ApiService::class.java)
    }
}