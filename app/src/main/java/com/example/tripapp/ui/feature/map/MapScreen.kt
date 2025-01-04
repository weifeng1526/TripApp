package com.example.tripapp.ui.feature.map


import android.location.Geocoder
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField


import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tripapp.ui.theme.*
import com.google.android.gms.maps.CameraUpdateFactory

import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.Marker
import com.google.android.libraries.places.api.model.Place
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import java.io.IOException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapScreen(
    viewModel: MapViewModel,
    navHostController: NavHostController) {
    val context = LocalContext.current
    //place
    val tripPlace by viewModel.tripPlaceList.collectAsState()
    val selectedPlace by viewModel.selectedTripPlace.collectAsState()
    var search by remember { mutableStateOf("") }
    var type =selectedPlace?.type.toString()
    var name = selectedPlace?.displayName.toString()
    var address = selectedPlace?.formattedAddress.toString()
    var latLng=selectedPlace?.location

    //景點資訊
    var poiInfo by remember { mutableStateOf(false) }
    var poiState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
//    地圖
    val myfavor = LatLng(25.02878879999997, 121.50661679999999)
    // CameraPositionState用於儲存地圖鏡頭狀態
    val cameraPositionState = rememberCameraPositionState {
        // 移動地圖到指定位置
        this.position = CameraPosition.fromLatLngZoom(myfavor, 15f)
    }
    // 儲存多個標記位置
    var positions by remember { mutableStateOf(listOf<LatLng>()) }
    // 暫存最新標記的位置，方便之後移動地圖至該標記
    var newPosition by remember { mutableStateOf<LatLng?>(null) }

    var sb by remember { mutableStateOf(StringBuilder()) }


    LaunchedEffect(Unit) {
        viewModel.initClient(context)
        viewModel.getPlaces(search =search , southWest = LatLng(22.045858, 119.426224), northEast =LatLng(25.161124, 122.343094) )
        viewModel.getPlaces(search =newPosition.toString() , southWest = LatLng(22.045858, 119.426224), northEast =LatLng(25.161124, 122.343094) )
    }

    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            onMapLongClick = { latLng ->
                // 長按地圖就將該點位置加入到儲存標記的list
                positions = positions + latLng
                // 更新最新標記位置
                newPosition = latLng
            },
            properties = MapProperties(// 是否呈現交通圖
                isTrafficEnabled = true,
                // 設定可捲動的範圍
                latLngBoundsForCameraTarget = LatLngBounds(
                    LatLng(22.045858, 119.426224),
                    LatLng(25.161124, 122.343094)
                ),
                // 設定地圖種類：NORMAL(一般圖，預設)、HYBRID(混合圖)、SATELLITE(衛星圖)、TERRAIN(地形圖)
                mapType = MapType.NORMAL,
                // 設定放大上限
                maxZoomPreference = 20f,
                // 設定縮小下限
                minZoomPreference = 5f
            ),
            // UI相關設定
            uiSettings = MapUiSettings(
                // 顯示指北針
                compassEnabled = true,
                // 允許旋轉手勢
                rotationGesturesEnabled = true,
                // 允許滑動手勢
                scrollGesturesEnabled = true,
                // 允許旋轉或縮放時可同時使用滑動手勢
                scrollGesturesEnabledDuringRotateOrZoom = true,
                // 開啟地圖傾斜手勢
                tiltGesturesEnabled = true,
                // 顯示縮放按鈕
                zoomControlsEnabled = true,
                // 允許縮放手勢
                zoomGesturesEnabled = true
            ),
            // 地圖載入完成後執行
            onMapLoaded = {
                Toast.makeText(context, "Map Loaded", Toast.LENGTH_SHORT).show()
            }
        ) {
            Marker(
//                Creating a state object during composition without using remember */
                state = rememberMarkerState(position = myfavor),
                title = "朴子當歸鴨"
            )
            //search產生的
            Marker(
                state = rememberMarkerState(position = latLng!!),
                title = name,
                onInfoWindowClick = {
                    poiInfo = true
                })
            //長按
            positions.forEach { position ->
                Marker(
                    state = MarkerState(position = position),
                    title = name,
                    snippet = address,
                    onInfoWindowClick = {
                        poiInfo = true
                    },
                    // 長按訊息視窗就移除該標記
                    onInfoWindowLongClick = {
                        positions = positions - position
                    }
                )
            }
        }


//輸入框
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .padding(8.dp)
        ) {
            OutlinedTextField(
                value = search,
                onValueChange = { search = it },
                label = { Text(text = "Search") },
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Blue,
                    unfocusedIndicatorColor = Color.Gray
                ),
                singleLine = true

            )
//手動增加的錨點
            Button(
                modifier = Modifier
                    .padding(0.dp)
                    .align(Alignment.CenterHorizontally),
                // 清除所有標記
                onClick = { positions = emptyList() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = purple200,
                    contentColor = purple300
                )
            ) {
                Text(text = "Clear All Makers", color = white100)
            }
        }

        Column(modifier = Modifier
            .fillMaxWidth()
            .align(Alignment.BottomCenter)) {


            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                item {

                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .background(color = purple200)) {


                        Column(modifier = Modifier.padding(start = 8.dp)) {
                            Text(type, maxLines = 1, fontSize = 16.sp)
                            Spacer(modifier = Modifier.padding(top = 8.dp))
                            Text(name, maxLines = 1, fontSize = 20.sp)
                            Spacer(modifier = Modifier.padding(top = 8.dp))
                            Text(
                                address.toString(),
                                maxLines = 2,
                                fontSize = 12.sp,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        Icon(imageVector = Icons.Default.Add,
                            contentDescription = "add",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(40.dp)
                                .clickable {})


                    }

                }
                items(1) {

                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .background(color = purple200)) {

                        Column(modifier = Modifier.padding(start = 8.dp)) {
                            Text(type, maxLines = 1, fontSize = 16.sp)
                            Spacer(modifier = Modifier.padding(top = 8.dp))
                            Text(name, maxLines = 1, fontSize = 20.sp)
                            Spacer(modifier = Modifier.padding(top = 8.dp))
                            Text(
                                address,
                                maxLines = 2,
                                fontSize = 12.sp,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        Icon(imageVector = Icons.Default.Add,
                            contentDescription = "add",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(40.dp)
                                .clickable {})


                    }

                }


            }
        }
        if (poiInfo) {
            ModalBottomSheet(
                modifier = Modifier.fillMaxHeight(),
                sheetState = poiState,
                onDismissRequest = { poiInfo = false }
            ) {
                Column(modifier = Modifier.fillMaxSize()) {
                    Text(text = name.toString(), fontSize = 20.sp, modifier = Modifier.padding(16.dp))
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp))
                    Text(text = type, fontSize = 16.sp, modifier = Modifier.padding(16.dp))
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp))
                    Text(
                        text = "地址${address}",
                        fontSize = 12.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp))
                }
            }
        }


    }
    // 移動地圖至最新標記所在位置(newMarker一旦改變就會執行)
    LaunchedEffect(newPosition) {
        newPosition?.let {
            cameraPositionState.animate(
                CameraUpdateFactory.newLatLngZoom(it, 15f)
            )
        }
    }

}


//taipei station
//台北車站 朴子當歸鴨
//桃園車站
