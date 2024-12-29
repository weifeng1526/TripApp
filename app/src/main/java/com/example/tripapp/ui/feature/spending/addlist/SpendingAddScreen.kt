package com.example.tripapp.ui.feature.spending.addlist

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tripapp.R
import com.example.tripapp.ui.feature.spending.list.SPENDING_LIST_ROUTE
import com.example.tripapp.ui.theme.*


@Composable
fun SpendingRoute(navHostController: NavHostController) {
    SpendingAddScreen(
        floatingButtonSaveClick = {
            //導頁專用語法
            navHostController.navigate(SPENDING_LIST_ROUTE)
        }
    )

}

@Preview
@Composable
fun PreviewSpendingRoute() {
    SpendingAddScreen()
}

@SuppressLint("RememberReturnType")
@Composable
fun SpendingAddScreen(
    floatingButtonSaveClick: () -> Unit = {}
) {
    val context = LocalContext.current
    var moneyInput by remember { mutableStateOf("") }
    var itemName by remember { mutableStateOf("") }
    var spendTime by remember { mutableStateOf("") }
    var swSplit by remember { mutableStateOf(false) }
    var txSplitMethod by remember { mutableStateOf("（公費支出）") }
    //下拉選單
    var ccyExpanded by remember { mutableStateOf(false) }
    var payByExpanded by remember { mutableStateOf(false) }
    var ccySelected by remember { mutableStateOf("日幣") }

    val btnSpendingClass = remember {
        mutableStateOf(
            mapOf(
                "食物" to "ic_cat_food",
                "交通" to "ic_cat_tfc",
                "票券" to "ic_cat_tix",
                "住宿" to "ic_cat_hotel",
                "購物" to "ic_cat_shop",
                "娛樂" to "ic_cat_ent",
                "其他" to "ic_cat_other"

            )
        )
    }

    val chmember = remember {
        mutableStateOf(
            mapOf(
                "ruby" to false,
                "selin" to false,
                "sean" to false
            )
        )
    }

    var ccyOptions = listOf(
        "日幣",
        "台幣"
    )



    var payByOptions = listOf(
        "付款人 Rubyyyyyer",
        "付款人 Sean",
        "付款人 Selin",
        "付款人 Brown",
        "付款人 Cony"
    )

    var selectedClassimg: String? by remember { mutableStateOf(null) }


//    var cbCrew by remember { mutableStateOf(false) }

    // full screen (no padding)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(white100)

    ) {
        // spending money input area
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(white100, white300)
                    ),
                )
        ) {
            // money input
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp, 20.dp),
            ) {
                // Drop-down currency
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
                        onClick = {
                            ccyExpanded = !ccyExpanded
//                                    Toast.makeText(context, "幣別下拉選單", Toast.LENGTH_SHORT).show()
                        },
                        border = BorderStroke(2.dp, white400),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = purple300, containerColor = Color.Transparent
                        ),
                        modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)

                    ) {
                        Text(
                            text = ccySelected,
                            color = purple300,
                        )
                        Image(
                            painter = painterResource(R.drawable.ic_popselect),
                            contentDescription = "pop",
                            modifier = Modifier
                                .size(22.dp)
                                .padding(8.dp, 0.dp, 0.dp, 0.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .padding(68.dp, 52.dp, 0.dp, 0.dp)
                    ) {
                        DropdownMenu(
                            expanded = ccyExpanded,
                            onDismissRequest = { ccyExpanded = false },
                            modifier = Modifier
                                .width(160.dp)
                                .padding(20.dp, 0.dp),
                            containerColor = white100,
                            border = BorderStroke(1.dp, white200)

                        ) {
                            ccyOptions.forEach {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .height(56.dp)
                                        .clickable {
                                            ccySelected = it
                                            Toast
                                                .makeText(context, it, Toast.LENGTH_SHORT)
                                                .show()
                                        }
                                ) {
                                    Text(text = it)

                                }
                            }
                        }
                    }
                }


                // spending money input
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState())
                        .padding(0.dp, 48.dp, 0.dp, 28.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
//                        Text(
//                            text = "20,000",
//                            fontSize = 44.sp,
//                            fontWeight = FontWeight.Bold,
//                            color = black900
//                        )
                        Column {

                            TextField(
                                value = moneyInput,
                                onValueChange = { moneyInput = it },
                                label = {
                                    Text(
                                        text = "請輸入金額",
                                        textAlign = TextAlign.End,
                                        color = black600,
                                        fontSize = 17.sp,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                    )
                                },
                                textStyle = TextStyle(fontSize = 30.sp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp, 20.dp, 0.dp, 0.dp),
                                singleLine = true,
                                colors = TextFieldDefaults.colors(
                                    unfocusedContainerColor = Color.Transparent,
                                    unfocusedIndicatorColor = white400,
                                    focusedContainerColor = Color.Transparent,
                                    focusedIndicatorColor = purple200,
                                )
                            )
                        }

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
        // pay by
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Button(
                onClick = {
                    payByExpanded = !payByExpanded
                    Toast.makeText(context, "付款人下拉選單", Toast.LENGTH_SHORT).show()
                },
                border = BorderStroke(2.dp, white400),
                colors = ButtonDefaults.buttonColors(
                    containerColor = white100, contentColor = purple300
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp, 0.dp)
                    .offset(x = 0.dp, y = (-24).dp),
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
                            modifier = Modifier.size(14.dp)
                        )
                    }
                }

            }
            Box(
                modifier = Modifier
                    .offset(x = 36.dp, y = (-20).dp)


            ) {

                DropdownMenu(
                    expanded = payByExpanded,
                    onDismissRequest = { payByExpanded = false },
                    modifier = Modifier
                        .width(340.dp)
                        .padding(20.dp, 0.dp),
                    containerColor = white100,
                    border = BorderStroke(1.dp, white200)


                ) {
                    payByOptions.forEach {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxSize()
                                .height(60.dp)
                                .clickable {
                                    Toast
                                        .makeText(context, it, Toast.LENGTH_SHORT)
                                        .show()
                                }
                        ) {
                            Text(text = it)
                        }
                    }
                }
            }
        }


        // spending money info area
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(20.dp, 0.dp)
                .offset(x = 0.dp, y = (-4).dp),
        ) {
            // spending class
            Text(
                text = "消費類別",
                fontSize = 15.sp,
                color = black900,
                modifier = Modifier.padding(4.dp, 0.dp)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 8.dp, 0.dp, 0.dp)
            ) {
                if (selectedClassimg == "食物") {
                    Image(painter = painterResource(R.drawable.ic_cat_food_s),
                        contentDescription = "food",
                        modifier = Modifier
                            .padding(0.dp, 0.dp, 4.dp, 0.dp)
                            .size(46.dp)
                            .clip(CircleShape)
                            .clickable {
                                selectedClassimg = null
                                Toast
                                    .makeText(context, "食物", Toast.LENGTH_SHORT)
                                    .show()
                            })
                } else {
                    Image(painter = painterResource(R.drawable.ic_cat_food),
                        contentDescription = "food",
                        modifier = Modifier
                            .padding(0.dp, 0.dp, 4.dp, 0.dp)
                            .size(46.dp)
                            .clip(CircleShape)
                            .clickable {
                                selectedClassimg = "食物"
                                Toast
                                    .makeText(context, "食物", Toast.LENGTH_SHORT)
                                    .show()
                            })
                }
                Image(painter = painterResource(R.drawable.ic_cat_tfc),
                    contentDescription = "traffic",
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 4.dp, 0.dp)
                        .size(46.dp)
                        .clip(CircleShape)
                        .clickable {
                            Toast
                                .makeText(context, "交通", Toast.LENGTH_SHORT)
                                .show()
                        })
                Image(painter = painterResource(R.drawable.ic_cat_tix),
                    contentDescription = "food",
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 4.dp, 0.dp)
                        .size(46.dp)
                        .clip(CircleShape)
                        .clickable {
                            Toast
                                .makeText(context, "票券", Toast.LENGTH_SHORT)
                                .show()
                        })
                Image(painter = painterResource(R.drawable.ic_cat_hotel),
                    contentDescription = "food",
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 4.dp, 0.dp)
                        .size(48.dp)
                        .clip(CircleShape)
                        .clickable {
                            Toast
                                .makeText(context, "住宿", Toast.LENGTH_SHORT)
                                .show()
                        })
                Image(painter = painterResource(R.drawable.ic_cat_shop),
                    contentDescription = "food",
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 4.dp, 0.dp)
                        .size(46.dp)
                        .clip(CircleShape)
                        .clickable {
                            Toast
                                .makeText(context, "購物", Toast.LENGTH_SHORT)
                                .show()
                        })
                Image(painter = painterResource(R.drawable.ic_cat_ent),
                    contentDescription = "food",
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 4.dp, 0.dp)
                        .size(46.dp)
                        .clip(CircleShape)
                        .clickable {
                            Toast
                                .makeText(context, "娛樂", Toast.LENGTH_SHORT)
                                .show()
                        })
                Image(painter = painterResource(R.drawable.ic_cat_other),
                    contentDescription = "food",
                    modifier = Modifier
                        .padding(0.dp, 0.dp, 4.dp, 0.dp)
                        .size(46.dp)
                        .clip(CircleShape)
                        .clickable {
                            Toast
                                .makeText(context, "其他", Toast.LENGTH_SHORT)
                                .show()
                        })
            }
            // item name
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
            // time
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

            // title + switch
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(20.dp, 20.dp, 20.dp, 0.dp)
                    .fillMaxWidth()

            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start,
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        Text(
                            text = "分帳方式", fontSize = 15.sp
                        )
                        Text(
                            text = txSplitMethod, fontSize = 15.sp
                        )
                        Row(
                            horizontalArrangement = Arrangement.End,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Switch(
                                checked = swSplit,
                                onCheckedChange = {
                                    swSplit = it
                                    if (swSplit) {
                                        txSplitMethod = "（均分）"
                                        chmember.value.forEach { name, isChecked ->
                                            chmember.value = chmember.value.toMutableMap().apply {
                                                this[name] = true
                                            }
                                        }

                                    } else {
                                        txSplitMethod = "（公費支出）"
                                        chmember.value.forEach { name, isChecked ->
                                            chmember.value = chmember.value.toMutableMap().apply {
                                                this[name] = false
                                            }
                                        }
                                    }
                                },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = white100,
                                    checkedTrackColor = purple100,
                                    uncheckedThumbColor = white100,
                                    uncheckedTrackColor = black300,
                                    uncheckedBorderColor = Color.Transparent,
                                )
                            )
                        }
                    }
                }
            }
            //check-box
            Column(
                modifier = Modifier
                    .padding(20.dp, 0.dp)
                    .fillMaxWidth()
            ) {
                checkBoxMemberList(chmember, floatingButtonSaveClick)
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)

    ) {
        FloatingActionButton(
            onClick = floatingButtonSaveClick,
            containerColor = purple200,
            shape = RoundedCornerShape(50),
            modifier = Modifier.align(Alignment.BottomEnd)

        ) {
            Image(
                painter = painterResource(R.drawable.ic_save),
                contentDescription = "add",
                modifier = Modifier
                    .size(33.dp)
            )
        }
    }
}

@Composable
private fun checkBoxMemberList(
    chmember: MutableState<Map<String, Boolean>>,
    floatingButtonSaveClick: () -> Unit = {},
) {
    chmember.value.forEach { (name, isChecked) ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 8.dp, 0.dp, 0.dp),
        ) {
            Image(
                painter = painterResource(R.drawable.ic_member_01),
                contentDescription = "member icon",
                modifier = Modifier.size(48.dp)
            )
            Text(
                text = name,
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
                    checked = isChecked,
                    onCheckedChange = {

                        chmember.value = chmember.value.toMutableMap().apply {
                            this[name] = it
                            Log.d("TAG_check", "test2$name : $it ")
                        }
                    }, colors = CheckboxDefaults.colors(
                        uncheckedColor = black300,
                        checkedColor = purple300,
                    )
                )
            }
        }
    }

}


