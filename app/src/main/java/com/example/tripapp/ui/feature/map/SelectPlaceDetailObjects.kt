package com.example.tripapp.ui.feature.map

import java.math.BigDecimal

//一筆景點的格式
data class SelectPlaceDetail(
    var schNo: Int = 0,               //跟行程拿編號
    var poiAdd: String = "",           // 景點地址
    var poiName: String = "",          // 景點名稱
    var poiLng: BigDecimal = BigDecimal("0.0"),  // 經度
    var poiLat: BigDecimal = BigDecimal("0.0"),  // 緯度
    var poiLab: String = "",           // 景點標籤
    var poiLike: Int = 1               // 收藏數量
)
data class PlaceDetail(
    var poiNo: Int = 0,               // 自動景點編號
    var poiAdd: String = "",           // 景點地址
    var poiName: String = "",          // 景點名稱
    var poiLng: BigDecimal = BigDecimal("0.0"),  // 經度
    var poiLat: BigDecimal = BigDecimal("0.0"),  // 緯度
    var poiLab: String = "",           // 景點標籤
    var poiLike: Int = 1               // 收藏數量
)