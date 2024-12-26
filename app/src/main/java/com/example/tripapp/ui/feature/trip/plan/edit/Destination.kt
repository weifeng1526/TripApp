package com.example.tripapp.ui.feature.trip.plan.edit

data class Destination(
    val dstNo: Int = 99,
    val schNo: Int = 99,
    val poiNo: Int = 99,
    val dstName: String = "測試名稱",
    val dstAddr: String = "測試地址",
    val dstPic: ByteArray = ByteArray(0),
    val dstDep: String = "測試敘述",
    val dstDate: String = "2000-01-01",
    val dstStart: String = "13:00",
    val dstEnd: String = "14:00",
)