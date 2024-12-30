package com.example.tripapp.ui.feature.trip.plan.restful

//一筆行程表的格式
data class Plan(
    var schNo: Int = 0,
    var memNo: Int = 0,
    var schState: Int = 0,
    var schName: String = "",
    var schCon: String = "",
    var schStart: String = "",
    var schEnd: String = "",
    var schCur: String = "",
    var schPic: ByteArray = ByteArray(0)
)
val plan = Plan(
    schNo = 0,
    memNo = 0,
    schState = 0,
    schName = "",
    schCon = "",
    schStart = "",
    schEnd = "",
    schCur = "",
    schPic = ByteArray(0)
)

//刪除成功的回傳
data class DeletePlanResponse(var isDelete: Boolean)

//一筆景點的格式
data class Destination(
    val dstNo: Int = 0,
    val schNo: Int = 0,
    val poiNo: Int = 0,
    val dstName: String = "",
    val dstAddr: String = "",
    val dstPic: ByteArray = ByteArray(0),
    val dstDep: String = "",
    val dstDate: String = "",
    val dstStart: String = "",
    val dstEnd: String = "",
)