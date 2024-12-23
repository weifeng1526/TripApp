package com.example.tripapp.ui.feature.spending.setting

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.tripapp.ui.feature.spending.list.SpendingListScreen


@Composable
fun SpendingRoute() {
    SpendingSetScreen()
}

@Preview
@Composable
fun PreviewSpendingRoute() {
    SpendingSetScreen()
}
@Composable
fun SpendingSetScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,


        ) {
        Text(
            text = "setting ",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }

}