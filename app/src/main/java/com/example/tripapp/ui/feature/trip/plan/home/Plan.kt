package com.example.tripapp.ui.feature.trip.plan.home

import androidx.compose.runtime.remember
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.LocalDate
import java.util.Date

data class Plan(
    val schNo: Int = 0,
    val memNo: Int = 0,
    val schState: String = "",
    val schName: String = "",
    val schCon: String = "",
    val schStart: String = "",
    val schEnd: String = "",
    val schCur: String = "",
    val schPic: ByteArray = ByteArray(0)
)

