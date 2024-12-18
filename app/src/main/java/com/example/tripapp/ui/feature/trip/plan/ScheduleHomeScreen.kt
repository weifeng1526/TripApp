package com.example.swithscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.tripapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleHomeScreen(navController: NavController) {
    val dst = List(60) { "DST${it + 1}" }.toMutableList()
    val dstDate = List(60) { "Date${it + 1}" }.toMutableList()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.padding(6.dp),
                title = {
                    Text(
                        text = "我的行程",
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.LightGray
                )
            )
        },
        content = { innerPadding ->
            // 使用 Modifier.padding(innerPadding) 並填滿剩餘空間
            //topbar以下畫面
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 6.dp, vertical = 4.dp)
                    .width(412.dp)
                    .height(742.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray)
                        .padding(2.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {},
                            modifier = Modifier
                                .size(52.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.schedule),
                                contentDescription = "schedule Icon",
                                modifier = Modifier.size(50.dp),
                                tint = Color.Unspecified
                            )
                        }
                        Text(
                            text = "行程表 x 60", //變數
                            style = TextStyle(
                                fontSize = 20.sp,
                                textAlign = TextAlign.Center
                            ),
                            modifier = Modifier.padding(end = 6.dp)
                        )
                    }
                    Spacer(
                        modifier = Modifier.weight(1f)
                            .padding(horizontal = 5.dp)
                    ) // 中間空白，讓其他內容排在最右邊
                    Row(
                        modifier = Modifier.wrapContentSize()
                            ,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.padding(horizontal = 10.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.White)
                        ) {
                            IconButton(
                                onClick = { navController.navigate(Screen.ScheduleCreate.name) },
                                modifier = Modifier.size(52.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.add),
                                    contentDescription = "Add Icon",
                                    modifier = Modifier.size(50.dp),
                                    tint = Color.Unspecified
                                )
                            }
                        }
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier.clip(RoundedCornerShape(8.dp))
                                .background(Color.White)
                        ) {
                            IconButton(
                                onClick = {},
                                modifier = Modifier.size(52.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.sort),
                                    contentDescription = "sort Icon",
                                    modifier = Modifier.size(50.dp),
                                    tint = Color.Unspecified
                                )
                            }
                        }
                    }
                }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(1), // 每列 1 個小卡
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(dst.size) { index ->
                        genTripCompose()
                    }
                }
            }
        }
    )
}

@Composable
fun genTripCompose() {
    //行程表
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .height(320.dp)
            .background(Color.LightGray)
    ) {
        Image(
            painter = painterResource(R.drawable.dst),//預設圖
            contentDescription = "Dst image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(8.dp))
                .fillMaxWidth()
                .height(200.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 8.dp, start = 8.dp, end = 8.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .fillMaxHeight()
                    .padding()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.6f)
                ) {
                    Text(
                        text = "Trip", //行程名稱
                        maxLines = 2,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    Text(
                        text = "2011/1/1 ~ 2011/2/2", //開始日期~結束日期
                        maxLines = 1,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f)
                ) {
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .size(52.dp)
                            .padding(1.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.menu),
                            contentDescription = "menu Icon",
                            modifier = Modifier.size(50.dp),
                            tint = Color.Unspecified
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                ) {
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .size(52.dp)
                            .padding(1.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.friends),
                            contentDescription = "friends Icon",
                            modifier = Modifier.size(50.dp),
                            tint = Color.Unspecified
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewScheduleHomeScreen() {
    ScheduleHomeScreen(rememberNavController())
}