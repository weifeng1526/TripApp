package com.example.swithscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tripapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleEditScreen(navController: NavController) {
    val dst = List(1) { "DST${it + 1}" }.toMutableList()
    val dstDate = List(1) { "Date${it + 1}" }.toMutableList()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.padding(6.dp),
                title = {
                    Text(
                        text = "規劃行程",
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
                        .height(100.dp)
                        .background(Color.LightGray)
                        .padding(6.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(0.5f),
                        verticalArrangement = Arrangement.spacedBy(
                            6.dp,
                            Alignment.CenterVertically
                        ),
                        horizontalAlignment = Alignment.Start
                    ) {
                        Text(
                            text = "台北三天兩夜",
                            style = TextStyle(
                                fontSize = 20.sp
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = "2024-12-1 ~ 2024-12-3",
                            style = TextStyle(
                                fontSize = 16.sp
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                        Text(
                            text = "最後編輯時間: 2024-11-1",
                            style = TextStyle(
                                fontSize = 16.sp
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(1), // 每列 1 個小卡
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(dst.size) { index ->
                        //genDayCompose(index)
                        genDstCompose()
                    }
                }
            }
        }
    )
}

@Composable
fun genDayCompose(day: Int) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "第${day}天",
            maxLines = 1,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun genDateCompose() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "2024-12-1 星期日",
            maxLines = 1,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.background(Color.LightGray)
        )
        Spacer(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 5.dp)
        ) // 中間空白，讓其他內容排在最右邊
        Text(
            text = "08:00 出發",
            maxLines = 1,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.background(Color.LightGray)
        )
    }
}

@Composable
fun genDstCompose() {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
        ) {
            Column(
                modifier = Modifier
            ) {
                Image(
                    painter = painterResource(R.drawable.dst),//預設圖
                    contentDescription = "Dst image",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .padding(8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .width(80.dp)
                        .height(80.dp)
                )
            }
            Column(
                modifier = Modifier
            ) {
                Row(
                    modifier = Modifier
                ) {
                    Text("北投圖書館")
                }
                Row(
                    modifier = Modifier
                ) {
                    Text("112台北市北投區光明路251號")
                }
            }
            Column(
                modifier = Modifier
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
        }
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "08:00",
                    modifier = Modifier.clickable {  }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "08:00",
                    modifier = Modifier.clickable { println("123") }
                )
            }
        }
    }
}


@Preview
@Composable
fun PreviewScheduleEditScreen() {
    ScheduleEditScreen(rememberNavController())
}