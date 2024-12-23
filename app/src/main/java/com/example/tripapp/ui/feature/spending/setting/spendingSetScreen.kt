package com.example.tripapp.ui.feature.spending.setting

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tripapp.R
import com.example.tripapp.ui.theme.white400


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
    val setting = listOf(
        "出遊幣別",
        "結算幣別",
        "分帳方式",
        "預設匯率",
        "公帳儲值"
    )



    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(0.dp,16.dp,0.dp,0.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            setting.forEachIndexed { index: Int, title: String ->

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {  }
                        .padding(20.dp)
                ) {
                    Text(text = setting[index])
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_next),
                            contentDescription = "next"
                        )
                    }
                }
                HorizontalDivider(
                    thickness = 1.dp,
                    color = white400
                )
            }
        }
    }
}