package com.example.tripapp.ui.feature.baggage

data class BagList(
    var memNo: Int,
    var schNo: Int,
    var itemNo: Int,
    var ready: Boolean,
)

data class Item(
    var itemNo: Int,
    var itemName: String,
    var itemType: Int,
    val itemExist: Boolean  // 判斷物品是否存在於 BAGLIST 中
)

// 行程資料類，包含行程編號 schNo
data class Trip(
    val schName: String,
    val schStart: String,
    val schEnd: String,
    val schNo: Int,
)

data class BagItems(
    var memNo: Int,
    var schNo: Int,
    var itemNo: Int,
    var itemName: String,
    var ready: Boolean,
)