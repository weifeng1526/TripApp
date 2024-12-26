package com.example.tripapp.ui.feature.trip.plan.home

import androidx.compose.runtime.remember
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.LocalDate
import java.util.Date

data class Plan(
    var schNo: Int = 99,
    var memNo: Int = 99,
    var schState: Int = 0,
    var schName: String = "測試名稱",
    var schCon: String = "測試國家",
    var schStart: String = "2000-01-01",
    var schEnd: String = "2000-01-01",
    var schCur: String = "測試幣別",
    var schPic: ByteArray = ByteArray(0)
)

