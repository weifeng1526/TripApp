package com.example.tripapp.ui.feature.baggage.itemlist

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tripapp.R

@Composable
fun AddItemRoute(navController: NavHostController) {
    AddItemScreen(navController)
}

@SuppressLint("RememberReturnType")
@Composable
fun AddItemScreen(navController: NavHostController) {
    var itemName = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center, // 垂直置中
        horizontalAlignment = Alignment.CenterHorizontally // 水平置中
    ) {
        // 替代 TopAppBar 的 Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.purple_200)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween // 左右分散排列
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "回到上一頁",
                    tint = colorResource(id = R.color.white_100)
                )
            }
            Text(
                text = "新增物品",
                style = MaterialTheme.typography.titleLarge,
                color = colorResource(id = R.color.white_100),
                modifier = Modifier
                    .weight(1f) // 將標題居中
                    .wrapContentWidth(Alignment.CenterHorizontally) // 填滿剩餘空間
            )
            // 我的會員按鈕
            IconButton(onClick = {
                navController.popBackStack() // 返回行李
            }) {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = "確認儲存",
                    tint = colorResource(id = R.color.white_100)
                )
            }
        }

        // 可展開的列表
        ExpandableLists(innerPadding = PaddingValues(12.dp))
    }
}

@Composable
fun ExpandableLists(innerPadding: PaddingValues) {
    val sections = remember {
        mutableStateListOf(
            "自訂" to mutableListOf("物品 A", "物品 B"),
            "衣物" to mutableListOf("襯衫", "外套", "褲子"),
            "隨身用品" to mutableListOf("水壺", "背包"),
            "個人用品" to mutableListOf("眼鏡", "手錶"),
            "洗漱用品" to mutableListOf("牙刷", "牙膏", "毛巾"),
            "化妝保養品" to mutableListOf("化妝水", "乳液"),
            "電子用品" to mutableListOf("手機", "筆記型電腦", "充電器"),
            "藥品" to mutableListOf("感冒藥", "止痛藥"),
            "文件支付類" to mutableListOf("護照", "機票", "信用卡")
        )
    }

    val expandedStates = remember(sections) {
        mutableStateMapOf<Int, Boolean>().apply {
            sections.indices.forEach { this[it] = true }
        }
    }

    val checkedState = remember { mutableStateMapOf<String, Boolean>() }

    // 用來保存編輯狀態和文字
    val editingItem = remember { mutableStateMapOf<String, Boolean>() }
    val editedText = remember { mutableStateMapOf<String, String>() }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            sections.forEachIndexed { index, section ->
                item(key = section.first) {
                    val title = section.first
                    val items = section.second
                    val isExpanded = expandedStates[index] == false

                    Column(
                        modifier = Modifier
                            .width(317.dp)
                            .border(1.dp, Color(0x8065558F), RoundedCornerShape(10.dp))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    expandedStates[index] = !(expandedStates[index] ?: true)
                                }
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Icon(
                                painter = painterResource(
                                    id = if (isExpanded) R.drawable.ah_baseline_arrow_drop_down_24 else R.drawable.ah_baseline_arrow_right_24
                                ),
                                contentDescription = if (isExpanded) "收起" else "展開",
                                modifier = Modifier.padding(end = 8.dp) // 圖標和文字間距
                            )
                            Text(
                                text = title,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.weight(1f) // 讓文字佔據剩餘空間
                            )
                        }

                        if (isExpanded) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
//                                verticalArrangement = Arrangement.spacedBy(0.dp)
                            ) {
                                items.forEach { item ->
                                    val isChecked = checkedState[item] ?: false

                                    Row(
                                        modifier = Modifier
                                            .width(317.dp)
                                            .height(52.dp)
                                            .border(
                                                1.dp,
                                                colorResource(id = R.color.purple_400),
                                                RoundedCornerShape(10.dp)
                                            )
                                            .background(
                                                if (isChecked) colorResource(id = R.color.purple_200)
                                                else colorResource(id = R.color.purple_100),
                                                RoundedCornerShape(10.dp)
                                            )
                                            .padding(
                                                start = 20.dp,
//                                                top = 2.dp,
                                                end = 20.dp,
//                                                bottom = 2.dp
                                            )
                                            .clickable { checkedState[item] = !isChecked },
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            modifier = Modifier.size(24.dp),
                                            painter = if (isChecked) painterResource(id = R.drawable.ashley_pickoption02) else painterResource(
                                                id = R.drawable.ashley_pickoption01
                                            ),
                                            contentDescription = if (isChecked) "已選擇" else "未選擇"
                                        )
                                        Spacer(modifier = Modifier.width(24.dp))

                                        // 當物品處於編輯模式時顯示 TextField，否則顯示文字
                                        if (editingItem[item] == true) {
                                            OutlinedTextField(
                                                value = editedText[item] ?: item,
                                                onValueChange = { editedText[item] = it },
                                                singleLine = true,
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .weight(1f)
                                                    .padding(end = 16.dp),
                                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                                            )
                                        } else {
                                            Text(
                                                text = editedText[item] ?: item,
                                                modifier = Modifier.weight(1f)
                                            )
                                        }

                                        // 編輯按鈕
                                        IconButton(onClick = {
                                            val isEditing = editingItem[item] == true
                                            editingItem[item] = !isEditing
                                            if (!isEditing) {
                                                editedText[item] = item // 加載原始文字
                                            }
                                        }) {
                                            Icon(
                                                modifier = Modifier.size(24.dp),
                                                painter = painterResource(id = if (editingItem[item] == true) R.drawable.ashley_edit_done else R.drawable.ashley_edit_text),
                                                contentDescription = if (editingItem[item] == true) "確定" else "編輯"
                                            )
                                        }

                                        // 刪除按鈕隱藏
                                        if (editingItem[item] != true) {
                                            IconButton(onClick = {
                                                sections[index].second.remove(item)
                                            }) {
                                                Icon(
                                                    imageVector = Icons.Filled.Delete,
                                                    contentDescription = "刪除"
                                                )
                                            }
                                        }
                                    }
                                }

                                // 新增物品輸入框
                                var newItem by remember { mutableStateOf("") }

                                Row(
                                    modifier = Modifier
                                        .width(317.dp)
                                        .height(52.dp)
                                        .border(
                                            1.dp,
                                            colorResource(id = R.color.purple_400),
                                            RoundedCornerShape(10.dp)
                                        )
                                        .background(
                                            colorResource(id = R.color.green_100),
                                            RoundedCornerShape(10.dp)
                                        )
                                        .padding(
                                            start = 20.dp,
//                                            top = 1.dp,
                                            end = 20.dp,
//                                            bottom = 1.dp
                                        ),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ashley_edit_plus),
                                        contentDescription = "新增選擇框",
                                        modifier = Modifier.size(24.dp)
                                    )
                                    Spacer(modifier = Modifier.width(24.dp))

                                    // 新增物品的輸入框
                                    OutlinedTextField(
                                        value = newItem,
                                        onValueChange = { newItem = it },
                                        placeholder = { Text("新增物品") },
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .weight(1f)
                                            .padding(end = 16.dp),
                                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                                    )

                                    // 新增按鈕
                                    IconButton(onClick = {
                                        if (newItem.isNotBlank()) {
                                            sections[index].second.add(newItem)
                                            newItem = ""
                                        }
                                    }) {
                                        Icon(
                                            imageVector = Icons.Filled.Done,
                                            contentDescription = "新增物品"
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewAddItemScreen() {
    AddItemScreen(navController = NavHostController(LocalContext.current))
}