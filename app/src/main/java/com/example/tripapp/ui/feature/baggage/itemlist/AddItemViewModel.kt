import android.icu.text.CaseMap.Title
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tripapp.ui.restful.BagList
import com.example.tripapp.ui.restful.Item
import com.example.tripapp.ui.restful.Plan
import com.ron.restdemo.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddItemViewModel : ViewModel() {

    private val tag = AddItemViewModel::class.java.simpleName

    // 保存所有物品
    private val _sections = MutableStateFlow<List<Pair<String, List<Item>>>>(emptyList())
    val sections: StateFlow<List<Pair<String, List<Item>>>> = _sections

    private val _expandedStates = MutableStateFlow<Map<Int, Boolean>>(emptyMap())
    val expandedStates: StateFlow<Map<Int, Boolean>> = _expandedStates

    private val _checkedState = MutableStateFlow<Map<Int, Boolean>>(emptyMap())
    val checkedState: StateFlow<Map<Int, Boolean>> = _checkedState

    init {
        // 初始化數據，從後端獲取所有物品
        fetchItems()
    }

    private fun fetchItems() {
        viewModelScope.launch {
            try {
//            // 從 API 請求數據
//                val apiResponse = RetrofitClient.apiService.fetchItems()
//                // 模拟从后端获取物品数据
                val groupedItems = listOf(
                    // 假设是从 API 获取到的物品数据，按类别分组
                    Pair("自订项目", listOf(Item(1, "自订項目1", 0), Item(2, "自訂項目2", 0))),
                    Pair("服饰", listOf(Item(5, "T恤", 1), Item(6, "牛仔褲", 1))),
                    Pair("配件", listOf(Item(11, "書包", 2), Item(12, "墨鏡", 2))),
                    // 这里可以继续添加更多分组...
                )

                // 更新物品数据
                _sections.value = groupedItems

                // 初始化展開状态，默认为收起
                _expandedStates.value = groupedItems.indices.associateWith { false }

                // 初始化勾选状态，默认为未选中
                _checkedState.value = groupedItems.flatMap { it.second }
                    .associate { it.itemNo to false }

            } catch (e: Exception) {
                Log.e(tag, "Error fetching items: ${e.message}")
            }
        }
    }

    fun updateExpandedState(sectionIndex: Int, isExpanded: Boolean) {
        _expandedStates.update { it.toMutableMap().apply { this[sectionIndex] = isExpanded } }
    }

    fun updateCheckedState(itemNo: Int, isChecked: Boolean) {
        _checkedState.update { it.toMutableMap().apply { this[itemNo] = isChecked } }
    }
}

    /**
     * 提交選擇的物品到後端
     */
    //    第五點待會處理
//    按下儲存按鈕時，將選中的物品加入 bag_list
//    fun saveSelectedItems(memNo: Int, schNo: Int) {
//        viewModelScope.launch {
//            try {
//                val selectedItems = _checkedState.value.filterValues { it }.keys
//                selectedItems.forEach { itemNo ->
//                    val bagListEntry = BagList(memNo, schNo, itemNo, false)
//                    RetrofitInstance.api.AddBagItem(bagListEntry)
//                }
//                Log.d("AddItemViewModel", "Items saved successfully.")
//            } catch (e: Exception) {
//                Log.e("AddItemViewModel", "Error saving items: ${e.message}")
//            }
//        }
//    }







//原本的功能
//    // 每个类别对应的物品列表
//    private val _sections = MutableStateFlow<List<Pair<String, List<Item>>>>(emptyList())
//    val sections = _sections.asStateFlow()
//
//    // 保存每个类别的展开状态
//    private val _expandedStates = MutableStateFlow<Map<Int, Boolean>>(emptyMap())
//    val expandedStates = _expandedStates.asStateFlow()
//
//    private val _checkedState = MutableStateFlow<Map<Int, Boolean>>(emptyMap())
//    val checkedState = _checkedState.asStateFlow()
//
//    // 保存编辑状态
//    private val _editingItem = MutableStateFlow<Map<String, Boolean>>(emptyMap())
//    val editingItem = _editingItem.asStateFlow()
//
//    // 保存编辑文字
//    private val _editedText = MutableStateFlow<Map<String, String>>(emptyMap())
//    val editedText = _editedText.asStateFlow()

//    fun fetchData() {
//        viewModelScope.launch {
//            // 获取所有物品
//            val items = GetItems()
//
//            // 获取已选择的物品（根据 BagList 判断）
//            val bagListItems = GetBagList() // 获取 BagList 数据
//            val selectedItems = bagListItems.map { it.itemNo } // 根据 BagList 获取已选择的物品编号
//
//            // 将物品列表和选中状态结合
//            val itemsWithState = items.map { item ->
//                item to selectedItems.contains(item.itemNo)
//            }
//
//            // 更新 sections 和选中状态
//            _sections.update { itemsWithState }
//            _checkedState.update { itemsWithState.associate { it.first.itemNo to it.second } }
//        }
//    }
//


//    舊的抓資料
//    fun fetchData() {
//        viewModelScope.launch {
//            val response = GetItems()
//            Log.e("section response", response.toString())
//            val items = response.groupBy { it.itemType.toString() }.toList()
//            _sections.update { items }
//        }
//    }
//
//
//    suspend fun GetItems(): List<Item> {
//        return try {
//            val response = RetrofitInstance.api.GetItems() // 不需要额外转换
//            Log.d(tag, "_data: ${response}")
//            response
//        } catch (e: Exception) {
//            Log.e(tag, "error: ${e.message}")
//            emptyList()
//        }
//    }

//    // 更新类别展开状态
//    fun updateExpandedState(index: Int, isExpanded: Boolean) {
//        _expandedStates.update { it.toMutableMap().apply { this[index] = isExpanded } }
//    }
//
//    // 更新物品勾选状态
//    fun updateCheckedState(itemNo: Int, isChecked: Boolean) {
//        _checkedState.update { it.toMutableMap().apply { this[itemNo] = isChecked } }
//    }
//
//    // 更新物品的编辑状态
//    fun updateEditingItem(item: String, isEditing: Boolean) {
//        _editingItem.update { it.toMutableMap().apply { this[item] = isEditing } }
//    }
//
//    // 更新物品的编辑文本
//    fun updateEditedText(item: String, newText: String) {
//        _editedText.update { it.toMutableMap().apply { this[item] = newText } }
//    }

//    // 从指定类别删除物品
//    fun removeItemFromSection(sectionIndex: Int, item: String) {
//        val section = _sections.value[sectionIndex]
//        val newItems = section.second.filterNot { it == item }
//
//        // 使用新的 Pair 替换旧的 Pair
//        _sections.update { currentSections ->
//            currentSections.toMutableList().apply {
//                this[sectionIndex] =
//                    section.copy(second = SnapshotStateList<Item>().apply { addAll(newItems) })
//            }
//        }
//    }
//
//    // 向指定类别添加物品
//    fun addItemToSection(sectionIndex: Int, item: String) {
//        val section = _sections.value[sectionIndex]
//        val newItems = section.second + item
//
//        // 使用新的 Pair 替换旧的 Pair
//        _sections.update { currentSections ->
//            currentSections.toMutableList().apply {
//                this[sectionIndex] =
//                    section.copy(second = SnapshotStateList<Item>().apply { addAll(newItems) })
//            }
//        }
//    }

//}

