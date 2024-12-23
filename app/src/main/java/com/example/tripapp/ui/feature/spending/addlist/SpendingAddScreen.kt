package com.example.tripapp.ui.feature.spending.addlist

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tripapp.R
import com.example.tripapp.ui.theme.*


@Composable
fun SpendingRoute() {
    SpendingAddScreen()
}

@Preview
@Composable
fun PreviewSpendingRoute() {
    SpendingAddScreen()
}

@Composable
fun SpendingAddScreen() {
    var itemName by remember { mutableStateOf("") }
    var spendTime by remember { mutableStateOf("") }
    var swSplit by remember { mutableStateOf(false) }
    var crew = listOf(
        "rubyyyyyer",
        "selin",
        "sean"
    )
    var ccy = listOf(
        "日幣",
        "台幣"
    )
    var cbCrew by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(white100)

    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(white100, white300)
                    ),
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp, 20.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "支出金額 ",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = black900
                    )
                    Button(
                        onClick = {},
                        border = BorderStroke(2.dp, white400),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = purple300, containerColor = Color.Transparent
                        ),
                        modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)

                    ) {
                        Text(
                            text = "日幣",
                            color = purple300,
                        )
                        Image(
                            painter = painterResource(R.drawable.ic_popselect),
                            contentDescription = "pop",
                            modifier = Modifier
                                .size(20.dp, 16.dp)
                                .padding(8.dp, 0.dp, 0.dp, 0.dp)
                        )
                    }

                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 48.dp, 0.dp, 32.dp),
                    horizontalAlignment = Alignment.End,


                    ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "20,000",
                            fontSize = 44.sp,
                            fontWeight = FontWeight.Bold,
                            color = black900
                        )
                        Text(
                            text = "JPY",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(12.dp, 0.dp, 0.dp, 0.dp),
                            color = black900

                        )
                    }
                }
            }
            HorizontalDivider(
                thickness = 2.dp, color = white400
            )

        }
        Button(
            onClick = {},
            border = BorderStroke(2.dp, white400),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent, contentColor = purple300
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp, 0.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    text = "付款人", fontSize = 15.sp

                )
                Text(
                    text = "Rubyyyyyer",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)
                )

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_popselect),
                        contentDescription = "pop",
                        modifier = Modifier.size(13.dp, 10.dp)

                    )
                }
            }


        }





        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp, 20.dp)
        ) {
            Text(
                text = "消費類別",
                fontSize = 15.sp,
                color = black900,
                modifier = Modifier.padding(4.dp,0.dp)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 8.dp, 0.dp, 0.dp)
            ) {
                Image(painter = painterResource(R.drawable.ic_cat_food),
                    contentDescription = "food",
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 4.dp, 0.dp)
                        .size(46.dp)
                        .clip(CircleShape)
                        .clickable { })
                Image(painter = painterResource(R.drawable.ic_cat_tfc),
                    contentDescription = "food",
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 4.dp, 0.dp)
                        .size(46.dp)
                        .clip(CircleShape)
                        .clickable { })
                Image(painter = painterResource(R.drawable.ic_cat_tix),
                    contentDescription = "food",
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 4.dp, 0.dp)
                        .size(46.dp)
                        .clip(CircleShape)
                        .clickable { })
                Image(painter = painterResource(R.drawable.ic_cat_hotel),
                    contentDescription = "food",
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 4.dp, 0.dp)
                        .size(48.dp)
                        .clip(CircleShape)
                        .clickable { })
                Image(painter = painterResource(R.drawable.ic_cat_shop),
                    contentDescription = "food",
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 4.dp, 0.dp)
                        .size(46.dp)
                        .clip(CircleShape)
                        .clickable { })
                Image(painter = painterResource(R.drawable.ic_cat_ent),
                    contentDescription = "food",
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 4.dp, 0.dp)
                        .size(46.dp)
                        .clip(CircleShape)
                        .clickable { })
                Image(painter = painterResource(R.drawable.ic_cat_other),
                    contentDescription = "food",
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 4.dp, 0.dp)
                        .size(46.dp)
                        .clip(CircleShape)
                        .clickable { })

            }
            TextField(
                value = itemName,
                onValueChange = { itemName = it },
                label = {
                    Text(
                        text = "名稱", color = black900, fontSize = 15.sp
                    )
                    Text(
                        text = "點擊以編輯 (選填)",
                        color = black600,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(52.dp, 0.dp, 0.dp, 0.dp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 20.dp, 0.dp, 0.dp),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = white100,
                    unfocusedIndicatorColor = white400,
                    focusedContainerColor = white100,
                    focusedIndicatorColor = purple200,
                )
            )

            TextField(
                value = spendTime,
                onValueChange = { spendTime = it },
                label = {
                    Text(
                        text = "時間", color = black900, fontSize = 15.sp
                    )
                    Text(
                        text = "點擊以編輯 (選填)",
                        color = black600,
                        fontSize = 15.sp,
                        modifier = Modifier.padding(52.dp, 0.dp, 0.dp, 0.dp)
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 20.dp, 0.dp, 0.dp),
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = white100,
                    unfocusedIndicatorColor = white400,
                    focusedContainerColor = white100,
                    focusedIndicatorColor = purple200,
                )
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(20.dp, 20.dp, 20.dp, 0.dp)
                    .fillMaxWidth()

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        Text(
                            text = "分帳方式",
                            fontSize = 15.sp
                        )
                        Text(
                            text = "（公費支出）",
                            fontSize = 15.sp
                        )
                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Switch(
                                checked = swSplit,
                                onCheckedChange = { swSplit = it },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = white100,
                                    checkedTrackColor = purple100,
                                    uncheckedThumbColor = white100,
                                    uncheckedTrackColor = black300,
                                    uncheckedBorderColor =Color.Transparent,

                                )
                            )
                        }
                    }


                }
            }
            Column(
                modifier = Modifier
                    .padding(20.dp, 6.dp)
                    .fillMaxWidth()
            ) {
                crew.forEachIndexed { index: Int, crew: String ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp,4.dp),
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_member),
                            contentDescription = "member icon",
                            modifier = Modifier.size(48.dp)
                        )
                        Text(
                            text = crew,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(12.dp, 8.dp)
                        )
                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                        ) {
                            Checkbox(
                                checked = cbCrew,
                                onCheckedChange = { cbCrew = it },
                                colors = CheckboxDefaults.colors(
                                    uncheckedColor = black300,
                                    checkedColor = purple300,
                                )

                            )
                        }

                    }


                }
            }
        }
    }
}