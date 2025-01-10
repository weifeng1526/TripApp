package com.example.tripapp.ui.feature.shop

import com.google.gson.annotations.SerializedName
import java.time.LocalDateTime

data class Order(
    @SerializedName("ordNo") val ordNo: Int,                // 訂單編號，預設為 0
    @SerializedName("memNo") val memNo: Int,                  // 會員編號，預設為 0
    @SerializedName("prodNo") val prodNo: Int,                 // 產品編號，預設為 0
    @SerializedName("prodName") val prodName: String,           // 產品名稱，預設為空字串
    @SerializedName("prodPrice") val prodPrice: Double,         // 訂單價格，預設為 0.0
    @SerializedName("ordDt") val ordDt: String, // 訂購日期，預設為當前時間
    @SerializedName("cardNo") val cardNo: String,             // 信用卡號，預設為空字串
    @SerializedName("expDate") val expDate: String,            // 信用卡到期日，預設為空字串
    @SerializedName("cvv") val cvv: String,                // 信用卡認證碼，預設為空字串
    @SerializedName("isSubmitted") val isSubmitted: Boolean     // 訂單是否提交，預設為 false
)

data class OrderRequest(
    val memNo: Int,
    val prodNo: Int,
    val prodName: String,
    val prodPrice: Double,
    val cardNo: String,
    val expDate: String,
    val cvv: String
)