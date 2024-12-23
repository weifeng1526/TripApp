package com.example.tripapp.ui.feature.shop

import com.example.tripapp.R

data class Product(
    var productNum: String = "",
    var productName: String = "",
    var price: Double = 0.0,
    var image: Int = R.drawable.ic_launcher_foreground,
    var longDescription: String = ""
) {

    override fun equals(other: Any?): Boolean {
        return this.productNum == (other as Product).productName
    }

    override fun hashCode(): Int {
        return productName.hashCode()
    }
}
