package com.example.tripapp.ui.feature.baggage.itemlist

data class Section(
    val title: String,
    val items: MutableList<Item>
)

data class Item(
    var name: String,
    var isChecked: Boolean = false,
    var isEditing: Boolean = false
)

