package com.example.tripapp.ui.feature.trip.repository.network

import com.example.tripapp.ui.feature.trip.dataObjects.Plan
import com.example.tripapp.ui.feature.trip.dataObjects.PlanInfo
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query


interface RetrofitService {
    @GET("schedule/info")
    suspend fun getPlansInfo(@Query("memId") memId: Int): List<PlanInfo>

    @GET("schedule/info")
    suspend fun getPlansInfoPage(
        @Query("memId") memId: Int,
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): List<PlanInfo>

    @Multipart
    @POST("schedule")
    suspend fun createPlan(
        @Part("planInfo") planInfo: RequestBody,
        @Part image: MultipartBody.Part?
    ): Int

    @POST("schedule")
    suspend fun createPlan(@Body plan: Plan): Plan?

    @POST("schedule/info")
    suspend fun createPlanInfo(@Body planInfo: PlanInfo): PlanInfo?

}

object RetrofitClient {
    val tag = "Network Response"
    val api: RetrofitService by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/TripAppEnd/") // Base URL
            .addConverterFactory(GsonConverterFactory.create()) // GSON for JSON conversion
            .build()
            .create(RetrofitService::class.java)
    }
}


