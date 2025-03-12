package com.example.tripapp.ui.feature.trip.dataObjects

import android.util.Log
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Date
import java.util.Locale


//一筆行程表的格式
data class Plan(
    var schNo: Int = 0,
    var memNo: Int = 0,
    var schState: Int = 0,
    var schName: String = "",
    var schCon: String = "",
    var schStart: Long? = null,
    var schEnd: Long? = null,
    var schCur: String = "",
    var schPic: ByteArray? = null,
    var schLastEdit: Long? = null
)

//一筆行程表的格式
data class PlanInfo(
    var schNo: Int = 0,
    var memNo: Int = 0,
    var schState: Int = 0,
    var schName: String = "",
    var schCon: String = "",
    var schStart: Long? = null,
    var schEnd: Long? = null,
    var schCur: String = "",
    var schLastEdit: Long? = null
)

//一筆行程表的格式
data class PlanImg(
    var schNo: Int = 0,
    var schPic: ByteArray = ByteArray(0),
)

//刪除成功的回傳
data class DeletePlanResponse(var isDelete: Boolean)
data class DeleteDstResponse(var isDelete: Boolean)

//一筆形成明細的格式
data class Destination(
    var dstNo: Int = 0,        // 景點編號
    var schNo: Int = 0,        // 行程編號
    var poiNo: Int = 0,        // 地點編號
    var dstName: String = "",  // 景點名稱
    var dstAddr: String = "",  // 景點地址
    var dstPic: ByteArray? = ByteArray(0),  // 景點圖片
    var dstDep: String = "",   // 描述
    var dstDate: String = "",  // 日期
    var dstStart: String = "", // 開始時間
    var dstEnd: String = "",   // 結束時間
    var dstInr: String = ""    // 間隔時間
)

fun convertToEpochSeconds(date: String): Long {
    val formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val localDate = LocalDate.parse(date, formatter)
    return localDate.atStartOfDay(ZoneId.systemDefault()).toEpochSecond()
}

fun convertTimeToSeconds(time: String): Int {

    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
    val localTime = LocalTime.parse(time, formatter)
    return localTime.toSecondOfDay()
}

fun convertSecondsToTimeString(seconds: Long): String {
    // 使用 Duration 來表示秒數
    val duration: Duration = Duration.ofSeconds(seconds)
    // 轉換為小時、分鐘和秒
    val hours: Long = duration.toHours()
    val minutes: Long = duration.toMinutes() % 60 // 除去小時部分
    val remainingSeconds: Long = duration.getSeconds() % 60 // 除去小時和分鐘部分
    // 格式化時間字串
    return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds)
}


fun isDateFormat(date: String): Boolean {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return try {
        //格式不對會出錯，要用trycatch
        LocalDate.parse(date, formatter)
        true
    } catch (e: DateTimeParseException) {
        false
    }
}

fun isTimeFormat(input: String): Boolean {
    return try {
        val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        LocalTime.parse(input, formatter)
        true
    } catch (e: DateTimeParseException) {
        false
    }
}

fun addTimes(time1: String, time2: String): String {
    try {
        val t1 = LocalTime.parse(time1)
        val t2 = LocalTime.parse(time2)
        val result = t1.plusHours(t2.hour.toLong()).plusMinutes(t2.minute.toLong())
        return result.format(DateTimeFormatter.ofPattern("HH:mm:ss"))
    } catch (e: Exception) {
        e.printStackTrace()
        Log.d("addTimes", "Time format error")
        return "00:00:00"
    }
}

fun getCurrentTimeAsString(): String {
    val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    val currentTime = Date()
    return sdf.format(currentTime)
}

fun convertLongToDate(millisecond: Long): LocalDate {
    return Instant.ofEpochMilli(millisecond)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
}

fun convertLongToDateTime(millisecond: Long): LocalDateTime {
    return Instant.ofEpochMilli(millisecond)
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime()
}

//為了crew相關的getRequest方便，crew沒這麼多欄，寫入時設定其中幾格欄一樣可以
data class CrewMmeber(
    var crewNo: Int = 0,
    var schNo: Int = 0,
    var memNo: Int = 0,
    var memIcon: ByteArray = byteArrayOf(0),
    var memName: String = "",
    var memEmail: String = "",
    var crewPeri: Byte = 0,
    var crewIde: Byte = 0,
    var crewName: String = "",
    var crewInvited: Byte = 0
)

private val _mapContryToCurrency = mapOf("" to "", "台灣" to "TWD", "日本" to "JPY")
val mapContryToCurrency: Map<String, String> get() = _mapContryToCurrency
val contries: Set<String> get() = mapContryToCurrency.keys
val currencies: Collection<String> get() = mapContryToCurrency.values

