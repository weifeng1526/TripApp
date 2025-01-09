package com.example.tripapp.ui.feature.baggage.baglist

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ron.restdemo.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// 行程資料類
data class Trip(
    val schName: String,
    val schStart: String,
    val schEnd: String,
    val schNo: Int = 0,
)
// 行程 ViewModel
class TripViewModel : ViewModel() {
    private val _trips = MutableStateFlow<List<Trip>>(emptyList())
    val trips: StateFlow<List<Trip>> = _trips

    private val _selectedTrip = MutableStateFlow<Trip?>(null)
    val selectedTrip: StateFlow<Trip?> = _selectedTrip

    // 更新行程清單
    fun updateTrips(newTrips: List<Trip>) {
        _trips.value = newTrips
    }

    // 選擇第一個行程
    fun selectFirstTrip() {
        _selectedTrip.value = _trips.value.firstOrNull()
    }

    // 選擇行程
    fun selectTrip(tripName: String) {
        _selectedTrip.value = _trips.value.find { it.schName == tripName }
    }
}

// 物品 ViewModel
class ItemViewModel : ViewModel() {
    private val _items = MutableStateFlow<List<String>>(emptyList())
    val items: StateFlow<List<String>> = _items

    // 根據行程更新物品清單
    fun updateItemsForTrip(trip: String) {
        val updatedItems = when (trip) {
            "Trip 1" -> listOf("Item A", "Item B", "Item C")
            "Trip 2" -> listOf("Item D", "Item E", "Item F")
            "Trip 3" -> listOf("Item G", "Item H", "Item I")
            else -> listOf("Default Item 1", "Default Item 2")
        }
        _items.value = updatedItems
    }
}

// 主 ViewModel
class BagViewModel : ViewModel() {
    private val tripViewModel = TripViewModel()
    private val itemViewModel = ItemViewModel()

    val trips: StateFlow<List<Trip>> = tripViewModel.trips
    val selectedTrip: StateFlow<Trip?> = tripViewModel.selectedTrip
    val items: StateFlow<List<String>> = itemViewModel.items


    init {
        fetchTrips()
    }


    // Fetch trips from API
    private fun fetchTrips() {
        viewModelScope.launch {
            try {
                val plans = RetrofitInstance.api.GetPlans() // 從 API 獲取行程
                val tripList = plans.map {
                    Trip(
                        schName = it.schName,
                        schStart = it.schStart,
                        schEnd = it.schEnd
                    )
                }
                tripViewModel.updateTrips(tripList) // 更新 trips
                tripViewModel.selectFirstTrip() // 自動選擇第一個行程
                tripViewModel.selectedTrip.value?.let {
                    itemViewModel.updateItemsForTrip(it.schName)
                }
            } catch (e: Exception) {
                // 處理錯誤，例如記錄或顯示錯誤消息
                e.printStackTrace()
            }
        }
    }

    fun onTripSelected(selectedOption: String) {
        tripViewModel.selectTrip(selectedOption)
        // 根據選中的行程更新物品
        tripViewModel.selectedTrip.value?.let { selectedTrip ->
            itemViewModel.updateItemsForTrip(selectedTrip.schName)
        }
    }
}

////假資料
//// 假設這是從資料庫取得的資料
//class TripViewModel : ViewModel() {
//
//    // 模擬的行程數據
//    val trips = mutableStateListOf("trip 1", "trip 2", "trip 3", "trip 4", "trip 5", "trip 6", "trip 7", "trip 8")
//
//    // 可擴展為從後端或數據庫讀取數據
//}
//
//
//class ItemViewModel : ViewModel() {
//    // 模擬的物品清單數據
//    val items = mutableStateListOf<String>().apply {
//        addAll((1..30).map { "Item $it" })
//    }
//
//    // 可擴展為從後端或數據庫讀取數據
//}