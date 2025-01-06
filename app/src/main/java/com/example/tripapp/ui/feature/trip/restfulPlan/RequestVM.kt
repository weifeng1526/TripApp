package com.example.tripapp.ui.feature.trip.restfulPlan

import android.util.Log
import androidx.lifecycle.ViewModel
import com.ron.restdemo.RetrofitInstance


/** 以下都還只是宣告，在coroutineScope呼叫才可使用RetrofitInstance發出API */
class RequestVM : ViewModel() {
    private val tag = "tag_RequestVM"

    /** 仕麟 */
    /** 取得一筆Plan */
    suspend fun getPlan(id: Int): Plan? {
        try {
            val response = RetrofitInstance.api.getPlan(id)
            Log.d(tag, "data: ${response}")
            return response
        } catch (e: Exception) {
            Log.e(tag, "error: ${e.message}")
            return null
        }
    }
    /** 取得所有Plan */
    suspend fun getPlans(): List<Plan>? {
        try {
            val response = RetrofitInstance.api.getPlans()
            Log.d(tag, "data: ${response}")
            return response
        } catch (e: Exception) {
            Log.e(tag, "error: ${e.message}")
            return emptyList()
        }
    }
    /** 寫入一筆Plan */
    suspend fun createPlan(request: Plan): Plan? {
        try {
            val response = RetrofitInstance.api.createPlan(request)
            Log.d(tag, "data: ${response}")
            return response
        } catch (e: Exception) {
            Log.e(tag, "error: ${e.message}")
            return null
        }
    }
    /** 更新一筆Plan */
    suspend fun updatePlan(request: Plan): Plan? {
        try {
            val response = RetrofitInstance.api.updatePlan(request)
            Log.d(tag, "data: ${response}")
            return response
        } catch (e: Exception) {
            Log.e(tag, "error: ${e.message}")
            return null
        }
    }
    /** 刪除一筆Plan */
    suspend fun deletePlan(id: Int): Boolean {
        try {
            val response = RetrofitInstance.api.deletePlan(id)
            Log.d(tag, "data: ${response}")
            return true
        } catch (e: Exception) {
            Log.e(tag, "error: ${e.message}")
            return false
        }
    }
    /** 取得某張表的行程明細 */
    suspend fun getDstsBySchedId(id: Int): List<Destination>? {
        try {
            val response = RetrofitInstance.api.getDstsBySchedId(id)
            Log.d(tag, "data: ${response}")
            return response
        } catch (e: Exception) {
            Log.e(tag, "error: ${e.message}")
            return emptyList()
        }
    }
    /** 寫入行程明細 */
    suspend fun addDst(dst: Destination): Destination? {
        try {
            val response = RetrofitInstance.api.createDest(dst)
            Log.d(tag, "data: ${response}")
            return response
        } catch (e: Exception) {
            Log.e(tag, "error: ${e.message}")
            return null
        }
    }
    /** 取得所有景點 */
    suspend fun getPois(): List<Poi>? {
        try {
            val response = RetrofitInstance.api.getPois()
            Log.d(tag, "data: ${response}")
            return response
        } catch (e: Exception) {
            Log.e(tag, "error: ${e.message}")
            return emptyList()
        }
    }

    /** 取得最後一個景點 */
    suspend fun getLastDst(): Destination? {
        try {
            val response = RetrofitInstance.api.getLastDst()
            Log.d(tag, "data: ${response}")
            return response
        } catch (e: Exception) {
            Log.e(tag, "error: ${e.message}")
            return null
        }
    }
    /** 更新景點 */
    suspend fun updateDst(dst: Destination): Destination? {
        try {
            val response = RetrofitInstance.api.updateDst(dst)
            Log.d(tag, "data: ${response}")
            return response
        } catch (e: Exception) {
            Log.e(tag, "error: ${e.message}")
            return null
        }
    }
    /** 依據會員編號找行程表 */
    suspend fun getPlanByMemId(id: Int): List<Plan>? {
        try {
            val response = RetrofitInstance.api.getPlanByMemId(id)
            Log.d(tag, "data: ${response}")
            return response
        } catch (e: Exception) {
            Log.e(tag, "error: ${e.message}")
            return emptyList()
        }
    }
    /** 依據國家名稱找行程表 */
    suspend fun getPlanByContry(name: String): List<Plan>? {
        try {
            val response = RetrofitInstance.api.getPlansByContry(name)
            Log.d(tag, "data: ${response}")
            return response
        } catch (e: Exception) {
            Log.e(tag, "error: ${e.message}")
            return emptyList()
        }
    }

    /** 偉峰 */

    /** Ruby */

    /** ㄒㄒ */

    /** 雅勳 */

    /** 喆 */

    /** 致意 */
}