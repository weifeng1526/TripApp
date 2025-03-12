package com.example.tripapp.ui.feature.trip.repository.network

import android.util.Log
import com.example.tripapp.ui.feature.trip.dataObjects.PlanInfo
import okhttp3.MultipartBody
import okhttp3.RequestBody

object PlanRepository {
    private val tag = "Http"
    private var httpClient = RetrofitClient.api
    private var plansInfo: List<PlanInfo> = emptyList()
    private var plansInfoPage: List<PlanInfo> = emptyList()

    suspend fun fetchPlansInfo(memId: Int): List<PlanInfo> {
        try {
            val response = httpClient.getPlansInfo(memId)
            Log.d(tag, "data: ${response}")
            return response
        } catch (e: Exception) {
            Log.e(tag, "error: ${e.message}")
            return emptyList()
        }
    }

    suspend fun fetchPlansInfoPage(memId: Int, limit: Int, offset: Int): List<PlanInfo> {
        try {
            val response = httpClient.getPlansInfoPage(memId, limit, offset)
            Log.d(tag, "data: ${response}")
            return response
        } catch (e: Exception) {
            Log.e(tag, "error: ${e.message}")
            return emptyList()
        }
    }

    suspend fun createPlan(planInfo: RequestBody, image: MultipartBody.Part?): Int {
        try {
            val response = httpClient.createPlan(planInfo, image)
            Log.d(tag, "data: ${response}")
            return response
        } catch (e: Exception) {
            Log.e(tag, "error: ${e.message}")
            return -1
        }
    }

    fun setPlansInfo(plansInfo: List<PlanInfo>) {
        this.plansInfo = plansInfo
    }

    fun getPlansInfo(): List<PlanInfo> {
        return this.plansInfo
    }

    fun setPlansInfoPage(plansInfoPage: List<PlanInfo>) {
        this.plansInfoPage = plansInfoPage
    }

    fun getPlansInfoPage(): List<PlanInfo> {
        return this.plansInfoPage
    }
}