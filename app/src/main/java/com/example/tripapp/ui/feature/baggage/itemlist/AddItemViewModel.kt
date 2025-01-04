import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tripapp.ui.restful.Plan
import com.ron.restdemo.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddItemViewModel : ViewModel() {

    private val tag = AddItemViewModel::class.java.simpleName

//    fun fetchData() {
//        viewModelScope.launch {
//            val response = getPlan(1)
//            sections.update { response }
//        }
//    }

    suspend fun getPlan(id: Int): Plan? {
        try {
            val response = RetrofitInstance.api.GetPlan(id)
            Log.d(tag, "data: ${response}")
            return response
        } catch (e: Exception) {
            Log.e(tag, "error: ${e.message}")
            return null
        }
    }

    // 每個類別對應的物品列表
    val sections = MutableStateFlow<List<Pair<String, SnapshotStateList<String>>>>(listOf())
//    val sections = mutableStateListOf(
//        "自訂" to mutableStateListOf("物品 A", "物品 B", "物品 C"),
//        "衣物" to mutableStateListOf("襯衫", "外套", "褲子", "帽子"),
//        "隨身用品" to mutableStateListOf("水壺", "背包"),
//        "個人用品" to mutableStateListOf("眼鏡", "手錶"),
//        "洗漱用品" to mutableStateListOf("牙刷", "牙膏", "毛巾"),
//        "化妝保養品" to mutableStateListOf("化妝水", "乳液"),
//        "電子用品" to mutableStateListOf("手機", "筆記型電腦", "充電器"),
//        "藥品" to mutableStateListOf("感冒藥", "止痛藥"),
//        "文件支付類" to mutableStateListOf("護照", "機票", "信用卡")
//    )

    // 用來保存每個類別的展開狀態 (MutableStateFlow)
    val expandedStates = MutableStateFlow<Map<Int, Boolean>>(
        sections.value.indices.associateWith { false }
    )

    // 用來保存每個物品的勾選狀態
    val checkedState = MutableStateFlow<Map<String, Boolean>>(
        sections.value.flatMap { it.second }.associateWith { false }
    )

    // 用來保存編輯狀態和物品的編輯文字
    val editingItem = MutableStateFlow<Map<String, Boolean>>(
        sections.value.flatMap { it.second }.associateWith { false }
    )
    val editedText = MutableStateFlow<Map<String, String>>(
        sections.value.flatMap { it.second }.associateWith { "" }
    )
}
