package com.example.tripapp.ui.feature.baggage.itemlist

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun BagItemRoute(navHostController: NavHostController) {
    AddItemScreen(navHostController)
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("RememberReturnType")
@Composable
fun AddItemScreen(navHostController: NavHostController) {
    var itemName = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("新增物品") },
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "回到上一頁")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
        ) {
            Text("輸入物品名稱")
            TextField(
                value = itemName.value,
                onValueChange = { itemName.value = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("物品名稱") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                // 在這裡可以處理新增物品的邏輯
                navHostController.popBackStack()  // 返回上一頁
            }) {
                Text("新增物品")
            }
        }
    }
}
