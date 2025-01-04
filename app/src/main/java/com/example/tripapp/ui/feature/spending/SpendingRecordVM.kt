package com.example.tripapp.ui.feature.spending

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tripapp.ui.restful.Plan
import com.ron.restdemo.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SpendingRecordVM : ViewModel() {

    private val tag = SpendingRecordVM::class.java.simpleName

    private val _plan = MutableStateFlow<List<Plan>>(listOf())
    val plan = _plan.asStateFlow()

    private val _spendingListInfo = MutableStateFlow(spendingListInfo())
    val spendingListInfo = _spendingListInfo.asStateFlow()


//    變數寫法
//    private val _title = MutableStateFlow<String?>(null)
//    val title = _title.asStateFlow()

    init {
        viewModelScope.launch {
            _spendingListInfo.value = spendingListInfo()
        }
    }

    fun initPlan() {
        viewModelScope.launch {
            val plan = getPlans()
            _plan.update { plan }
        }
    }


    suspend fun getPlans(): List<Plan> {
        try {
            val response = RetrofitInstance.api.GetPlans()
            Log.d(tag, "data: ${response}")
            return response
        } catch (e: Exception) {
            Log.e(tag, "error: ${e.message}")
            return listOf()
        }
    }


    private fun spendingListInfo(): List<SpendingRecord> {
        return listOf(
            SpendingRecord(
                payByUserName = "小明",
                className = "餐飲",
                itemName = "拉麵",
                dateTime = "2023-11-22 19:30",
                totalAmount = 800.0,
                perPersonAmount = 800.0,
                numberOfPeople = 1
            ),
            SpendingRecord(
                payByUserName = "小美",
                className = "交通",
                itemName = "地鐵一日券",
                dateTime = "2023-11-23 09:00",
                totalAmount = 800.0,
                perPersonAmount = 800.0,
                numberOfPeople = 1
            ),
            SpendingRecord(
                payByUserName = "小美",
                className = "票券",
                itemName = "演唱會門票",
                dateTime = "2023-11-24 18:00",
                totalAmount = 5000.0,
                perPersonAmount = 2500.0,
                numberOfPeople = 2
            ),
            SpendingRecord(
                payByUserName = "小美",
                className = "住宿",
                itemName = "飯店住宿費",
                dateTime = "2023-11-25 14:00",
                totalAmount = 3000.0,
                perPersonAmount = 1500.0,
                numberOfPeople = 2
            ),
            SpendingRecord(
                payByUserName = "胖虎",
                className = "購物",
                itemName = "運動用品",
                dateTime = "2023-11-26 11:00",
                totalAmount = 2000.0,
                perPersonAmount = 2000.0,
                numberOfPeople = 1
            ),
            SpendingRecord(
                payByUserName = "小明",
                className = "娛樂",
                itemName = "遊樂園門票",
                dateTime = "2023-11-27 13:00",
                totalAmount = 4000.0,
                perPersonAmount = 2000.0,
                numberOfPeople = 2
            ),
            SpendingRecord(
                payByUserName = "小明",
                className = "餐飲",
                itemName = "咖啡",
                dateTime = "2023-11-28 16:00",
                totalAmount = 300.0,
                perPersonAmount = 300.0,
                numberOfPeople = 1
            ),
            SpendingRecord(
                payByUserName = "大雄",
                className = "交通",
                itemName = "計程車費",
                dateTime = "2023-11-29 20:00",
                totalAmount = 500.0,
                perPersonAmount = 500.0,
                numberOfPeople = 1
            ),
            SpendingRecord(
                payByUserName = "大雄",
                className = "其他",
                itemName = "雜費",
                dateTime = "2023-11-30 12:00",
                totalAmount = 200.0,
                perPersonAmount = 200.0,
                numberOfPeople = 1
            ),
            SpendingRecord(
                payByUserName = "靜香",
                className = "購物",
                itemName = "書籍",
                dateTime = "2023-12-01 15:00",
                totalAmount = 1000.0,
                perPersonAmount = 1000.0,
                numberOfPeople = 1
            )
        )

    }


}