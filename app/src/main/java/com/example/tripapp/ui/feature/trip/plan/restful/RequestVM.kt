package com.example.tripapp.ui.feature.trip.plan.restful

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ron.restdemo.RetrofitInstance


/** 以下都還只是宣告，在coroutineScope呼叫才可使用RetrofitInstance發出API */
class RequestVM : ViewModel() {
    private val tag = "tag_RequestVM"
    /** 取得一筆Plan */
    suspend fun GetPlan(id: Int): Plan? {
        try {
            val response = RetrofitInstance.api.GetPlan(id)
            Log.d(tag, "data: ${response}")
            return response
        } catch (e: Exception) {
            Log.e(tag, "error: ${e.message}")
            return null
        }
    }

    /** 取得所有Plan */
    suspend fun GetPlans(): List<Plan> {
        try {
            val response = RetrofitInstance.api.GetPlans()
            Log.d(tag, "data: ${response}")
            return response
        } catch (e: Exception) {
            Log.e(tag, "error: ${e.message}")
            return emptyList()
        }
    }

    /** 寫入一筆Plan */
    suspend fun CreatePlan(request: Plan): Plan? {
        try {
            val response = RetrofitInstance.api.CreatePlan(request)
            Log.d(tag, "data: ${response}")
            return response
        } catch (e: Exception) {
            Log.e(tag, "error: ${e.message}")
            return null
        }
    }
    /** 更新一筆Plan */
    suspend fun UpdatePlan(request: Plan): Plan? {
        try {
            val response = RetrofitInstance.api.UpdatePlan(request)
            Log.d(tag, "data: ${response}")
            return response
        } catch (e: Exception) {
            Log.e(tag, "error: ${e.message}")
            return null
        }
    }
    /** 刪除一筆Plan */
    suspend fun DeletePlan(id: Int): Boolean {
        try {
            val response = RetrofitInstance.api.DeletePlan(id)
            Log.d(tag, "data: ${response}")
            return true
        } catch (e: Exception) {
            Log.e(tag, "error: ${e.message}")
            return false
        }
    }
}