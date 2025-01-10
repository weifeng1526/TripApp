package com.example.tripapp.ui.feature.shop

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.plus

class OrderVM : ViewModel() {
    private val tag = "tag_OrderVM"
    private val _ordersState = MutableStateFlow(emptyList<Order>())
    val ordersState: StateFlow<List<Order>> = _ordersState.asStateFlow()

//    private var orderCounter = 1
//
//    fun addOrder(
//        memNo: Int,
//        prodNo: Int,
//        prodName: String,
//        prodPrice: Double,
//        cardNo: String,
//        expDate: String,
//        cvv: String,
//        isSubmitted: Boolean = false
//    ) {
//        val currentDateTime = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
//        val order = Order(
//            ordNo = orderCounter,  // 使用當前的訂單編號
//            memNo = memNo,
//            prodNo = prodNo,
//            prodName = prodName,
//            prodPrice = prodPrice,
//            ordDt = currentDateTime,  // 使用當前時間作為訂單時間
//            cardNo = cardNo,
//            expDate = expDate,
//            cvv = cvv,
//            isSubmitted = isSubmitted
//        )
//        _ordersState.update { currentOrders ->
//            currentOrders + order
//        }
//        orderCounter++
//    }

    fun removeOrder(ordNo: Int) {
        _ordersState.update { currentOrders ->
            currentOrders.filter { it.ordNo != ordNo }
        }
    }

    fun markAllOrdersSubmitted() {
        viewModelScope.launch {
            saveOrdersToDatabase(_ordersState.value)
            _ordersState.update { orders ->
                orders.map { it.copy(isSubmitted = true) }
            }
        }
    }

    fun submitOrderToDatabase(
        order: OrderRequest,
        onSuccess: (Order) -> Unit,  // 成功回調，返回訂單資料
        onError: (String) -> Unit     // 失敗回調，返回錯誤訊息
    ) {
        viewModelScope.launch {
            try {
                // 發送網路請求將訂單資料送到後端
                val response = ShopApiService.RetrofitInstance.api.addOrder(order)

                // 檢查回應是否成功
                if (response.isSuccessful) {
                    // 這裡印出 API 回應的資料
                    Log.d("OrderSubmit", "API Response: ${response.body()}")

                    // 將 response.body() 轉換為 OrderResponse 物件
                    val orderResponse: OrderResponse? = response.body()

                    // 檢查 orderResponse 是否為 null
                    if (orderResponse != null) {
                        // 從 OrderResponse 中取得真正的 Order 物件
                        val actualOrder = orderResponse.order

                        _ordersState.update { currentOrders ->
                            currentOrders + actualOrder
                        }
                        // 執行成功回調，並傳遞訂單資料
                        onSuccess(actualOrder)
                        val orderNumber = actualOrder.ordNo // 取得訂單編號
                        val orderDateTime = actualOrder.ordDt// 取得訂單時間
                        Log.d("OrderSubmit", "訂單成功提交，訂單編號: $orderNumber")
                        Log.d("OrderSubmit", "訂單時間: $orderDateTime")
                    } else {
                        // 處理後端返回空資料的情況
                        Log.e("OrderSubmit", "訂單提交失敗: 後端返回空資料")
                        onError("訂單提交失敗: 後端返回空資料")
                    }
                } else {
                    // 處理 HTTP 請求錯誤
                    val errorMessage = response.errorBody()?.string() ?: "未知錯誤"
                    Log.e("OrderSubmit", "訂單提交失敗: $errorMessage")
                    onError("訂單提交失敗: $errorMessage")
                }
            } catch (e: Exception) {
                // 處理任何異常
                Log.e("OrderSubmit", "提交時發生錯誤: ${e.message}")
                onError("提交時發生錯誤: ${e.message}")
            }
        }
    }

//    fun submitOrderToDatabase(order: OrderRequest) {
//        viewModelScope.launch {
//            try {
//                val response = ShopApiService.RetrofitInstance.api.addOrder(order)
//                if (response.isSuccessful) {
//                    Log.d("OrderSubmit", "訂單提交成功")
//                } else {
//                    Log.e("OrderSubmit", "訂單提交失敗: ${response.errorBody()?.string()}")
//                }
//            } catch (e: Exception) {
//                Log.e("OrderSubmit", "提交時發生錯誤: ${e.message}")
//            }
//        }
//    }

    // 模擬儲存訂單到資料庫的函式
    private suspend fun saveOrdersToDatabase(orders: List<Order>) =
        withContext(Dispatchers.IO) {
            orders.forEach { order ->
                println("儲存訂單 ${order.ordNo} 到資料庫") // 模擬耗時操作
                Thread.sleep(500) // 模擬延遲（實際情況中應替換為資料庫操作）
            }
        }
}