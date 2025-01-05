package com.example.tripapp.ui.feature.spending

import com.google.gson.annotations.SerializedName
import java.sql.Timestamp
import java.util.Date

data class SpendingRecord(
    @SerializedName("CostNo") val costNo: Int = 0,  // 消費紀錄編號
    @SerializedName("schNo") val schNo: Int = 0, // 行程編號
//    @SerializedName("schName") val scName: String = "", // 行程名稱
    @SerializedName("CostType") val costType: Byte = 0, // 消費類別
    @SerializedName("CostPrice") val costPrice: Double = 0.0, // 消費金額
    @SerializedName("PaidBy") val paidBy: Int = 0, // 付款人
    @SerializedName("PaidByName") val paidByName: String = "", // 會員名稱
    @SerializedName("CostTime") val costTime: String = "", // 消費時間
    @SerializedName("CostPex") val costPex: Double = 0.0, // 公費支出
    @SerializedName("CurEnd") val curEnd: String = "" // 結算幣別


//    val payByUserName: String = "", // 會員名稱
//    val className: String = "", // 類別 (例如: 交通、餐飲)
//    val itemName: String = "", // 項目名稱 (例如: 高鐵車票)
//    val dateTime: String = "", // 日期
//    val totalAmount: Double = 0.00, // 總花費金額
//    val perPersonAmount: Double = 0.00, // 每人均分金額
//    val numberOfPeople: Int = 0, // 分攤人數
//    val currency: String = "JPY" // 貨幣單位，預設為日圓

)






