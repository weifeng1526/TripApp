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
    var itemType: Int
)
