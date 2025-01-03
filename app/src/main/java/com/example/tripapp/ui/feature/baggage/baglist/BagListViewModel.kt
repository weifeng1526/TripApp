package com.example.tripapp.ui.feature.baggage.baglist

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

// 假設這是從資料庫取得的資料
class TripViewModel : ViewModel() {
    // 模擬的行程數據
    val trips = mutableStateListOf("trip 1", "trip 2", "trip 3", "trip 4", "trip 5", "trip 6", "trip 7", "trip 8")

    // 可擴展為從後端或數據庫讀取數據
}


class ItemViewModel : ViewModel() {
    // 模擬的物品清單數據
    val items = mutableStateListOf<String>().apply {
        addAll((1..30).map { "Item $it" })
    }

    // 可擴展為從後端或數據庫讀取數據
}