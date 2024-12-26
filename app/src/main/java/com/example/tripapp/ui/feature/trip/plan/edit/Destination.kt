package com.example.tripapp.ui.feature.trip.plan.edit

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
) {

}