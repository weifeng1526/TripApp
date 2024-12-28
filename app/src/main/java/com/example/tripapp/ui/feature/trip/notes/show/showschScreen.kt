package com.example.tripview.show

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tripapp.R
import com.example.tripapp.ui.theme.black100
import com.example.tripapp.ui.theme.black800
import com.example.tripapp.ui.theme.purple300
import com.example.tripapp.ui.theme.white100


@Composable
fun Showsch() {
    Column(
        verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
                .width(412.dp)
                .height(43.dp)
                .background(color = colorResource((R.color.white_300)))
                .horizontalScroll(rememberScrollState())
        ) {
            Row(
                modifier = Modifier
                    .width(86.dp)
                    .height(43.dp)
            )
            {
                Column(
                    modifier = Modifier
                        .width(46.dp)
                        .height(43.dp)
                ) {
                    Text(
                        text = "12/16 週一",
                    )
                }
                Image(
                    painter = painterResource(R.drawable.sun),
                    contentDescription = "image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(25.dp)
                        .offset(y = 8.dp)
                )
            }
            Row(
                modifier = Modifier
                    .width(86.dp)
                    .height(43.dp)
            )
            {
                Column(
                    modifier = Modifier
                        .width(46.dp)
                        .height(43.dp)
                ) {
                    Text(
                        text = "12/17 週二",
                    )
                }
                Image(
                    painter = painterResource(R.drawable.rain),
                    contentDescription = "image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(25.dp)
                        .offset(y = 8.dp)
                )
            }
            Row(
                modifier = Modifier
                    .width(86.dp)
                    .height(43.dp)
            )
            {
                Column(
                    modifier = Modifier
                        .width(46.dp)
                        .height(43.dp)
                ) {
                    Text(
                        text = "12/18 週三",
                    )
                }
                Image(
                    painter = painterResource(R.drawable.cloud),
                    contentDescription = "image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(25.dp)
                        .offset(y = 8.dp)
                )
            }
            Row(
                modifier = Modifier
                    .width(86.dp)
                    .height(43.dp)
            )
            {
                Column(
                    modifier = Modifier
                        .width(46.dp)
                        .height(43.dp)
                ) {
                    Text(
                        text = "12/19 週四",
                    )
                }
                Image(
                    painter = painterResource(R.drawable.sun),
                    contentDescription = "image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(25.dp)
                        .offset(y = 8.dp)
                )
            }
            Row(
                modifier = Modifier
                    .width(86.dp)
                    .height(43.dp)
            )
            {
                Column(
                    modifier = Modifier
                        .width(46.dp)
                        .height(43.dp)
                ) {
                    Text(
                        text = "12/20 週五",
                    )
                }
                Image(
                    painter = painterResource(R.drawable.cloud),
                    contentDescription = "image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(25.dp)
                        .offset(y = 8.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier.fillMaxSize(1f).verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Canvas(
                        modifier = Modifier
                            .size(22.dp)
                            .offset(10.dp)
                    ) {
                        drawCircle(
                            color = purple300, // 外框顏色
                            radius = size.minDimension / 2 // 圓的半徑
                        )
                        // 內部填充圓
                        drawCircle(
                            color = white100, // 內部顏色
                            radius = size.minDimension / 2 - 3.dp.toPx() // 縮小3dp作為邊框
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "10:00", color = black800,
                    )
                }
                Row() {
                    Canvas(modifier = Modifier.fillMaxHeight()) {
                        drawLine(
                            color = purple300,
                            start = Offset(x = 59f, y = 0f),
                            end = Offset(x = 59f, y = size.height),
                            strokeWidth = 10f
                        )
                    }
                    Spacer(modifier = Modifier.width(40.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(R.drawable.aaa),
                            contentDescription = null,
                            modifier = Modifier.size(338.dp, 190.dp)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .offset(25.dp)
                        ) {
                            Text(
                                text = "商場",
                                modifier = Modifier.fillMaxWidth(0.8f),
                                fontSize = 22.sp
                            )
                            Image(
                                painter = painterResource(R.drawable.edit_note),
                                contentDescription = null,
                                modifier = Modifier.size(30.dp),
                            )
                        }
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .height(110.dp)
                        .fillMaxWidth(1f)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Canvas(
                            modifier = Modifier
                                .size(22.dp)
                                .offset(10.dp)
                        ) {
                            drawCircle(
                                color = purple300, // 外框顏色
                                radius = size.minDimension / 2 // 圓的半徑
                            )
                            // 內部填充圓
                            drawCircle(
                                color = white100, // 內部顏色
                                radius = size.minDimension / 2 - 3.dp.toPx() // 縮小3dp作為邊框
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "10:10", color = black800,
                        )
                    }
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Canvas(modifier = Modifier.fillMaxHeight()) {
                            drawLine(
                                color = purple300,
                                start = Offset(x = 59f, y = 0f),
                                end = Offset(x = 59f, y = size.height),
                                strokeWidth = 10f
                            )
                        }
                        Image(
                            painter = painterResource(R.drawable.baseline_directions_walk_24),
                            contentDescription = null,
                            modifier = Modifier
                                .offset(x = 60.dp, y = 15.dp)
                                .size(70.dp)
                        )
                        Text(
                            text = "10分鐘", fontSize = 22.sp,
                            modifier = Modifier.offset(x = 70.dp, y = 30.dp)
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Canvas(
                        modifier = Modifier
                            .size(22.dp)
                            .offset(10.dp)
                    ) {
                        drawCircle(
                            color = purple300, // 外框顏色
                            radius = size.minDimension / 2 // 圓的半徑
                        )
                        // 內部填充圓
                        drawCircle(
                            color = white100, // 內部顏色
                            radius = size.minDimension / 2 - 3.dp.toPx() // 縮小3dp作為邊框
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "10:20", color = black800,
                    )
                }
                Row() {
                    Canvas(modifier = Modifier.fillMaxHeight()) {
                        drawLine(
                            color = purple300,
                            start = Offset(x = 59f, y = 0f),
                            end = Offset(x = 59f, y = size.height),
                            strokeWidth = 10f
                        )
                    }
                    Spacer(modifier = Modifier.width(40.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(R.drawable.aaa),
                            contentDescription = null,
                            modifier = Modifier.size(338.dp, 190.dp)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .offset(25.dp)
                        ) {
                            Text(
                                text = "商場",
                                modifier = Modifier.fillMaxWidth(0.8f),
                                fontSize = 22.sp
                            )
                            Image(
                                painter = painterResource(R.drawable.edit_note),
                                contentDescription = null,
                                modifier = Modifier.size(30.dp),
                            )
                        }
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .height(110.dp)
                        .fillMaxWidth(1f)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Canvas(
                            modifier = Modifier
                                .size(22.dp)
                                .offset(10.dp)
                        ) {
                            drawCircle(
                                color = purple300, // 外框顏色
                                radius = size.minDimension / 2 // 圓的半徑
                            )
                            // 內部填充圓
                            drawCircle(
                                color = white100, // 內部顏色
                                radius = size.minDimension / 2 - 3.dp.toPx() // 縮小3dp作為邊框
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "11:20", color = black800,
                        )
                    }
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Canvas(modifier = Modifier.fillMaxHeight()) {
                            drawLine(
                                color = purple300,
                                start = Offset(x = 59f, y = 0f),
                                end = Offset(x = 59f, y = size.height),
                                strokeWidth = 10f
                            )
                        }
                        Image(
                            painter = painterResource(R.drawable.baseline_directions_car_24),
                            contentDescription = null,
                            modifier = Modifier
                                .offset(x = 60.dp, y = 15.dp)
                                .size(70.dp)
                        )
                        Text(
                            text = "10分鐘", fontSize = 22.sp,
                            modifier = Modifier.offset(x = 70.dp, y = 30.dp)
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .height(250.dp)
                    .fillMaxWidth(1f)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Canvas(
                        modifier = Modifier
                            .size(22.dp)
                            .offset(10.dp)
                    ) {
                        drawCircle(
                            color = purple300, // 外框顏色
                            radius = size.minDimension / 2 // 圓的半徑
                        )
                        // 內部填充圓
                        drawCircle(
                            color = white100, // 內部顏色
                            radius = size.minDimension / 2 - 3.dp.toPx() // 縮小3dp作為邊框
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "10:20", color = black800,
                    )
                }
                Row() {
                    Canvas(modifier = Modifier.fillMaxHeight()) {
                        drawLine(
                            color = purple300,
                            start = Offset(x = 59f, y = 0f),
                            end = Offset(x = 59f, y = size.height),
                            strokeWidth = 10f
                        )
                    }
                    Spacer(modifier = Modifier.width(40.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                    ) {
                        Image(
                            painter = painterResource(R.drawable.aaa),
                            contentDescription = null,
                            modifier = Modifier.size(338.dp, 190.dp)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .offset(25.dp)
                        ) {
                            Text(
                                text = "商場",
                                modifier = Modifier.fillMaxWidth(0.8f),
                                fontSize = 22.sp
                            )
                            Image(
                                painter = painterResource(R.drawable.edit_note),
                                contentDescription = null,
                                modifier = Modifier.size(30.dp),
                            )
                        }
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .height(110.dp)
                        .fillMaxWidth(1f)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Canvas(
                            modifier = Modifier
                                .size(22.dp)
                                .offset(10.dp)
                        ) {
                            drawCircle(
                                color = purple300, // 外框顏色
                                radius = size.minDimension / 2 // 圓的半徑
                            )
                            // 內部填充圓
                            drawCircle(
                                color = white100, // 內部顏色
                                radius = size.minDimension / 2 - 3.dp.toPx() // 縮小3dp作為邊框
                            )
                        }
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = "11:20", color = black800,
                        )
                    }
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Canvas(modifier = Modifier.fillMaxHeight()) {
                            drawLine(
                                color = purple300,
                                start = Offset(x = 59f, y = 0f),
                                end = Offset(x = 59f, y = size.height),
                                strokeWidth = 10f
                            )
                        }
                        Image(
                            painter = painterResource(R.drawable.baseline_directions_car_24),
                            contentDescription = null,
                            modifier = Modifier
                                .offset(x = 60.dp, y = 15.dp)
                                .size(70.dp)
                        )
                        Text(
                            text = "10分鐘", fontSize = 22.sp,
                            modifier = Modifier.offset(x = 70.dp, y = 30.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Showsch()
}