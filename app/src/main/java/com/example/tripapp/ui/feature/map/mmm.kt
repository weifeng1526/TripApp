package com.example.tripapp.ui.feature.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.google.maps.android.compose.GoogleMap
import kotlinx.coroutines.launch

//@OptIn
@Composable
fun map(){
    var search by remember { mutableStateOf("") }
    //記住輸入關鍵字
    var info by remember { mutableIntStateOf(0)  }
    //用於新增資料顯示欄
//    val sheetState = rememberModalBottomSheetState()
//    val scope = rememberCoroutineScope()
//    var showBottomSheet by remember { mutableStateOf(false) }
    Box(modifier = Modifier.fillMaxSize()){
        GoogleMap(modifier = Modifier.fillMaxSize()) {


    }
        Column (modifier = Modifier.fillMaxWidth().height(16.dp).align(Alignment.TopCenter)
        ){
            OutlinedTextField(
                value = search,
                onValueChange = {search = it},
                modifier = Modifier.fillMaxWidth(),
                label = { Text("請輸入地點") }
            )
        }
        Column (modifier = Modifier.fillMaxWidth().height(16.dp).align(Alignment.BottomCenter)){

            Spacer(modifier = Modifier.fillMaxWidth().padding(8.dp))
            LazyRow (modifier = Modifier.fillMaxWidth()){
                items(info){

                }
            }
        }
//        Scaffold(
//            floatingActionButton = {
//                ExtendedFloatingActionButton(
//                    text = { Text("Show bottom sheet") },
//                    icon = { Icon(Icons.Filled.Add, contentDescription = "") },
//                    onClick = {
//                        showBottomSheet = true
//                    }
//                )
//            }
//        ) { contentPadding ->
//            // Screen content
//
//            if (showBottomSheet) {
//                ModalBottomSheet(
//                    onDismissRequest = {
//                        showBottomSheet = false
//                    },
//                    sheetState = sheetState
//                ) {
//                    // Sheet content
//                    Button(onClick = {
//                        scope.launch { sheetState.hide() }.invokeOnCompletion {
//                            if (!sheetState.isVisible) {
//                                showBottomSheet = false
//                            }
//                        }
//                    }) {
//                        Text("Hide bottom sheet")
//                    }
//                }
//            }
//        }
}
}

@Preview(showBackground = true)
@Composable
fun DemoPreview(){
map()
}