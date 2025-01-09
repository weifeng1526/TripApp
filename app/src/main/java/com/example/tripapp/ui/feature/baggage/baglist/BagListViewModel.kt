package com.example.tripapp.ui.feature.baggage.baglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tripapp.ui.feature.baggage.BagList
import com.example.tripapp.ui.feature.baggage.Item
import com.ron.restdemo.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// 行程資料類，包含行程編號 schNo
data class Trip(
    val schName: String,
    val schStart: String,
    val schEnd: String,
    val schNo: Int,
)

data class ItemWithReady(
    val itemNo: Int,
    val itemName: String,
    val ready: Boolean // 是否準備好
)


class TripViewModel : ViewModel() {
    private val _trips = MutableStateFlow<List<Trip>>(emptyList())
    val trips: StateFlow<List<Trip>> = _trips

    private val _selectedTrip = MutableStateFlow<Trip?>(null)
    val selectedTrip: StateFlow<Trip?> = _selectedTrip

    fun updateTrips(newTrips: List<Trip>) {
        _trips.value = newTrips
    }

    fun selectFirstTrip() {
        _selectedTrip.value = _trips.value.firstOrNull()
    }

    fun selectTrip(schNo: Int) {
        _selectedTrip.value = _trips.value.find { it.schNo == schNo }
    }
}

class ItemViewModel : ViewModel() {
    private val _items = MutableStateFlow<List<ItemWithReady>>(emptyList())
    val items: StateFlow<List<ItemWithReady>> = _items

    fun updateItemsForTrip(memNo: Int, schNo: Int) {
        viewModelScope.launch {
            try {
                // 從 API 獲取所有物品和行李清單
                val allItems = RetrofitInstance.api.GetItems() // 所有物品資料
                val bagItems = RetrofitInstance.api.GetBagItems(memNo, schNo) // 行李狀態資料
                // 整合物品名稱和準備狀態
                val itemList = allItems.map { item ->
                    val matchingBagItem = bagItems.find { it.itemNo == item.itemNo }
                    ItemWithReady(
                        itemNo = item.itemNo,
                        itemName = item.itemName,
                        ready = matchingBagItem?.ready ?: false // 默認未準備
                    )
                }
                _items.value = itemList
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

// 主 ViewModel
class BagViewModel : ViewModel() {
    private val tripViewModel = TripViewModel()
    private val itemViewModel = ItemViewModel()
    val trips: StateFlow<List<Trip>> = tripViewModel.trips
    val selectedTrip: StateFlow<Trip?> = tripViewModel.selectedTrip
    val items: StateFlow<List<ItemWithReady>> = itemViewModel.items

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
                        schEnd = it.schEnd,
                        schNo = it.schNo // 確保包含 schNo
                    )
                }
                tripViewModel.updateTrips(tripList) // 更新 trips
                tripViewModel.selectFirstTrip() // 自動選擇第一個行程
                tripViewModel.selectedTrip.value?.let {
                    itemViewModel.updateItemsForTrip(1, it.schNo)// 假設 memNo 是 1，根據需要修改
                }
            } catch (e: Exception) {
                // 處理錯誤，例如記錄或顯示錯誤消息
                e.printStackTrace()
            }
        }
    }

    fun onTripSelected(schNo: Int) {
        tripViewModel.selectTrip(schNo) // 更新選中的行程
        tripViewModel.selectedTrip.value?.let { selectedTrip ->
            itemViewModel.updateItemsForTrip(1, selectedTrip.schNo) // 假設 memNo 是 1，根據需要修改
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