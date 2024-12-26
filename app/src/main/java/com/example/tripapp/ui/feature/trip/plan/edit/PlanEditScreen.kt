package com.example.tripapp.ui.feature.trip.plan

import android.app.AlertDialog
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tripapp.R
import com.example.tripapp.ui.feature.trip.plan.create.PLAN_CREATE_ROUTE
import com.example.tripapp.ui.feature.trip.plan.edit.Destination
import com.example.tripapp.ui.feature.trip.plan.edit.PLAN_EDIT_ROUTE
import com.example.tripapp.ui.feature.trip.plan.edit.PlanEditViewModel
import com.example.tripapp.ui.feature.trip.plan.home.PLAN_HOME_ROUTE
import com.example.tripapp.ui.feature.trip.plan.home.Plan
import com.example.tripapp.ui.feature.trip.plan.home.PlanHomeViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Formatter

//理想是新增所有卡都可以隨時拖拉
@Composable
fun PlanEditScreen(
    navController: NavController,
    planEditViewModel: PlanEditViewModel = viewModel(),
    planHomeViewModel: PlanHomeViewModel,
    schNo: Int
) {
    var addDstBtAtTop by remember { mutableStateOf(false) }
    //所有dst
    val dsts by planEditViewModel.dstsState.collectAsState()
    //所有行程
    val schs by planHomeViewModel.plansState.collectAsState()
    //從HOME or Create帶過來的值
    var schNo = schNo
    //schNo的所有dst
    var dstsInSch = dsts.filter {
        it.schNo == schNo
    }
    //schNo的sch
    var sch = schs.find {
        it.schNo == schNo
    } ?: Plan()
    //日期轉換
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    //行程的出發日期、結束日期
    var schStart = LocalDate.parse(sch.schStart, formatter)
    var schEnd by remember { mutableStateOf(LocalDate.parse(sch.schEnd, formatter)) }
    //行程天數list、行程日期list、星期幾list
    var days = (0..ChronoUnit.DAYS.between(schStart, schEnd).toInt()).toList()
    var dates = days.map { schStart.plusDays(it.toLong()) }
    var dayOfWeek = dates.map { it.dayOfWeek.toString() }
    Log.d("Debug schEnd", "message: ${sch.schEnd}")
    Log.e("Error schEnd", "message: ${sch.schEnd}")
    Log.d("Debug days", "message: $days")
    Log.e("Error days", "message: $days")
    Log.d("Debug dates", "message: $dates")
    Log.e("Error dates", "message: $dates")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 6.dp, vertical = 4.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.Top)
    ) {
        //跳轉
        Button(onClick = {
            //navController.navigate(PLAN_HOME_ROUTE)
            navController.popBackStack(route = PLAN_HOME_ROUTE, inclusive = false)
        }) { }
        Button(
            onClick = {
                val newDst = Destination(
                    schNo = 1,
                    dstDate = "2024-12-03"
                )
                planEditViewModel.addDst(newDst)
            },
        ) {
            Text(text = dsts.size.toString())
        }
        //行程資訊
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(Color.LightGray),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                verticalArrangement = Arrangement.spacedBy(
                    6.dp,
                    Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = sch.schName,
                    style = TextStyle(
                        fontSize = 24.sp
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "${sch.schStart} ~ ${sch.schEnd}",
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
        //新增景點、新增天數
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .background(Color.LightGray),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally)
        ) {
            Row(
                modifier = Modifier
                    .padding(4.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { addDstBtAtTop = true },
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.add_location),
                        contentDescription = "Add Icon",
                        modifier = Modifier.size(30.dp),
                        tint = Color.Unspecified
                    )
                }
                Text(
                    text = "新增景點", //變數
                    style = TextStyle(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.padding(end = 6.dp)
                )
            }
            Spacer(Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .padding(4.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {},
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.add_box),
                        contentDescription = "Add Icon",
                        modifier = Modifier.size(30.dp),
                        tint = Color.Unspecified
                    )
                }
                Text(
                    text = "新增間隔時間", //變數
                    style = TextStyle(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.padding(end = 6.dp)
                )
            }
            Spacer(Modifier.weight(1f))
            Row(
                modifier = Modifier
                    .padding(4.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
                    .clickable {
                        schEnd = schEnd.plusDays(1)
                        sch.schEnd = schEnd.toString()
                        planHomeViewModel.setPlan(sch)
                        Log.d("Debug schEnd", "message: ${sch.schEnd}")
                        Log.e("Error schEnd", "message: ${sch.schEnd}")
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.add_box),
                    contentDescription = "Add Icon",
                    modifier = Modifier.size(30.dp),
                    tint = Color.Unspecified
                )
                Text(
                    text = "新增天數", //變數
                    style = TextStyle(
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.padding(end = 6.dp)
                )
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(1), // 每列 1 個小卡
            modifier = Modifier.fillMaxWidth()
        ) {
            items(days.size) { index ->
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    ShowDayRow(
                        day = days[index],
                        date = dates[index],
                        dayOfWeek = dayOfWeek[index],
                        dsts = dstsInSch,
                        sch = sch
                    )
                    //因動態新增，增加padding，父Column已有內部垂直6.dp
                    Spacer(modifier = Modifier.padding(0.dp))
                }
            }
        }
        if (addDstBtAtTop) {
            mainAddDstAlertDialog(
                add = dsts.size,
                onDismissRequest = { addDstBtAtTop = false }
            )
        }
    }
}


@Composable
fun ShowDayRow(
    day: Int,
    date: LocalDate,
    dayOfWeek: String,
    dsts: List<Destination>,
    sch: Plan
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "第${day}天",
            maxLines = 1,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(6.dp)
                .background(Color.White)
        )
        Text(
            text = "${date} ${dayOfWeek}",
            maxLines = 1,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(6.dp)
                .background(Color.White)
        )
    }
    dsts.forEachIndexed { index, dst ->
        if (sch.schNo != 0 && dst.dstDate == date.toString()) {
            ShowDstRow(dst = dst)
        }
    }
}

@Composable
fun ShowDstRow(dst: Destination) {
    var addDstBtAtRows by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.dst), // 預設圖
                contentDescription = "Dst image",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .padding(6.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .size(100.dp)
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = dst.dstName,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = dst.dstAddr,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start
                )
                Text(
                    text = "${dst.dstStart} ~ ${dst.dstEnd}",
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.End, // 右對齊
            verticalAlignment = Alignment.Top
        ) {
            IconButton(
                onClick = { addDstBtAtRows = true },
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.more_horiz),
                    contentDescription = "menu Icon",
                    modifier = Modifier.size(30.dp),
                    tint = Color.Unspecified
                )
            }
        }
        if (addDstBtAtRows) {
            addDstAlertDialogByRows(
                onDismissRequest = { addDstBtAtRows = false }
            )
        }
    }
}

@Composable
fun ShowIntervalRow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .clickable { },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "間隔：0小時30分鐘",
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(6.dp)
        )
        IconButton(
            onClick = {},
            modifier = Modifier
                .size(32.dp)
                .padding(6.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.disabled),
                contentDescription = "remove Icon",
                modifier = Modifier.size(30.dp),
                tint = Color.Unspecified
            )
        }
    }
}

@Composable
fun addDstAlertDialogByRows(
    onDismissRequest: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {},
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable(
                            onClick = onDismissRequest
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.add_location),
                        contentDescription = "map Icon",
                        modifier = Modifier.size(30.dp),
                        tint = Color.Unspecified
                    )
                    Text("在下方加入景點")
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.add_box),
                        contentDescription = "map Icon",
                        modifier = Modifier.size(30.dp),
                        tint = Color.Unspecified
                    )
                    Text("在下方加入間隔時間")
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.delete),
                        contentDescription = "map Icon",
                        modifier = Modifier.size(30.dp),
                        tint = Color.Unspecified
                    )
                    Text("刪除此景點")
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.turn_right),
                        contentDescription = "map Icon",
                        modifier = Modifier
                            .size(30.dp)
                            .rotate(90f),
                        tint = Color.Unspecified
                    )
                    Text("返回")
                }
            }
        },
        confirmButton = {}
    )
}

@Composable
fun mainAddDstAlertDialog(
    add: Int,
    onDismissRequest: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = {},
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { add + 1 },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.travel_explore),
                        contentDescription = "map Icon",
                        modifier = Modifier.size(30.dp),
                        tint = Color.Unspecified
                    )
                    Text("地圖")
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.favorite),
                        contentDescription = "map Icon",
                        modifier = Modifier.size(30.dp),
                        tint = Color.Unspecified
                    )
                    Text("我的收藏")
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.shopping_bag),
                        contentDescription = "map Icon",
                        modifier = Modifier.size(30.dp),
                        tint = Color.Unspecified
                    )
                    Text("套裝行程")
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.turn_right),
                        contentDescription = "map Icon",
                        modifier = Modifier
                            .size(30.dp)
                            .rotate(90f),
                        tint = Color.Unspecified
                    )
                    Text("返回")
                }
            }
        },
        confirmButton = {}
    )
}

@Preview
@Composable
fun PreviewPlanEditScreen() {
    PlanEditScreen(
        rememberNavController(),
        viewModel(),
        viewModel(),
        schNo = 2
    )
}