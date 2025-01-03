import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel

class AddItemViewModel : ViewModel() {
    // 每個類別對應的物品列表
    val sections = mutableStateListOf(
        "自訂" to mutableStateListOf("物品 A", "物品 B","物品 C"),
        "衣物" to mutableStateListOf("襯衫", "外套", "褲子","帽子"),
        "隨身用品" to mutableStateListOf("水壺", "背包"),
        "個人用品" to mutableStateListOf("眼鏡", "手錶"),
        "洗漱用品" to mutableStateListOf("牙刷", "牙膏", "毛巾"),
        "化妝保養品" to mutableStateListOf("化妝水", "乳液"),
        "電子用品" to mutableStateListOf("手機", "筆記型電腦", "充電器"),
        "藥品" to mutableStateListOf("感冒藥", "止痛藥"),
        "文件支付類" to mutableStateListOf("護照", "機票", "信用卡")
    )

    // 用來保存每個類別的展開狀態
    val expandedStates = mutableStateMapOf<Int, Boolean>().apply {
        sections.indices.forEach { this[it] = false }
    }

    // 用來保存每個物品的勾選狀態
    val checkedState = mutableStateMapOf<String, Boolean>()

    // 用來保存編輯狀態和物品的編輯文字
    val editingItem = mutableStateMapOf<String, Boolean>()
    val editedText = mutableStateMapOf<String, String>()
}
