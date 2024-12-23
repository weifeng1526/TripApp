package com.example.tripapp.ui.feature.trip.plan.home

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PlanHomeViewModel: ViewModel() {
    private var _planState = MutableStateFlow(Plan())
    val planState = _planState.asStateFlow()

    private val _plansState = MutableStateFlow(emptyList<Plan>())
    val plansState: StateFlow<List<Plan>> = _plansState.asStateFlow()

    init {
        _plansState.update {
            fetchPlans()
        }
    }

    private fun fetchPlans(): List<Plan> {
        return listOf(
            Plan(
                schNo = 1,
                memNo = 101,
                schState = "Active",
                schName = "台北三天兩夜",
                schCon = "探索台北的城市風光，享受夜市美食。",
                schStart = "2024-12-01",
                schEnd = "2024-12-03",
                schCur = "TWD",
                schPic = byteArrayOf(0x89.toByte(), 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A) // 假設 PNG 開頭字節
            ),
            Plan(
                schNo = 2,
                memNo = 102,
                schState = "Active",
                schName = "台中四天三夜",
                schCon = "拜訪台中的著名景點，品嚐在地美食。",
                schStart = "2024-12-04",
                schEnd = "2024-12-07",
                schCur = "TWD",
                schPic = byteArrayOf(0xFF.toByte(), 0xD8.toByte(), 0xFF.toByte(), 0xE0.toByte()) // 假設 JPEG 開頭字節
            )
        )
    }
}

