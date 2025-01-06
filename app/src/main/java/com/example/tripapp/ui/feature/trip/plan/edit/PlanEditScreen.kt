package com.example.tripapp.ui.feature.trip.plan

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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tripapp.R
import com.example.tripapp.ui.feature.trip.plan.edit.PlanEditViewModel
import com.example.tripapp.ui.feature.trip.plan.home.PLAN_HOME_ROUTE
import com.example.tripapp.ui.feature.trip.plan.home.PlanHomeViewModel
import com.example.tripapp.ui.feature.trip.restfulPlan.Destination
import com.example.tripapp.ui.feature.trip.restfulPlan.Plan
import com.example.tripapp.ui.feature.trip.restfulPlan.Poi
import com.example.tripapp.ui.feature.trip.restfulPlan.RequestVM
import com.ron.restdemo.RetrofitInstance
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Calendar

//理想是新增所有卡都可以隨時拖拉
@Composable
fun PlanEditScreen(
    navController: NavController,
    planEditViewModel: PlanEditViewModel,
    planHomeViewModel: PlanHomeViewModel,
    requestVM: RequestVM,
    schNo: Int
) {
    Log.d("d test", "test!!!!!!!!!!!!")
    //測試用poi
    var poi by remember { mutableStateOf(Poi()) }
    //CoroutineScope
    val coroutineScope = rememberCoroutineScope()
    //所有行程明細
    val dsts by planEditViewModel.dstsState.collectAsState()
    //單個行程明細
    val dst by planEditViewModel.dstState.collectAsState()
    //某個日期的行程明細
    val dstsForDate by planEditViewModel.dstsForDateState.collectAsState()
    //所有行程表
    val plans by planHomeViewModel.plansState.collectAsState()
    //單個行程表
    val plan by planHomeViewModel.planState.collectAsState()
    //加入景點按鈕
    var addDstBtAtTop by remember { mutableStateOf(false) }
    //觀察行程開始日期、結束日期、最後編輯日期、對應的：行程天數、行程日期、星期
    var planStart by remember { mutableStateOf("") }
    var planEnd by remember { mutableStateOf("") }
    var planLastEdit by remember { mutableStateOf("") }
    var days by remember { mutableStateOf(emptyList<Int>()) }
    var dates by remember { mutableStateOf(emptyList<LocalDate>()) }
    var dayOfWeek by remember { mutableStateOf(emptyList<Int>()) }
    //觀察已選日期、第幾天、星期幾
    var selectedDate by remember { mutableStateOf("") }
    var selectedDay by remember { mutableStateOf(0) }
    var selectedDayOfWeek by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        coroutineScope {
            var planResponse: Plan? = null
            var dstsResponse: List<Destination>?
            async {
                planResponse = requestVM.getPlan(schNo)
                planResponse?.let {
                    planHomeViewModel.setPlan(it)
                }
                Log.d("async1", "async1")
            }.await()
            async {
                dstsResponse = requestVM.getDstsBySchedId(schNo)
                dstsResponse?.let {
                    planEditViewModel.setDsts(it)
                }
                Log.d("async2", "async2")
            }.await()
        }
    }
    //日期轉換
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    if (plan.schNo != 0) {
        planStart = plan.schStart
        planEnd = plan.schEnd
        planLastEdit = plan.schLastEdit
        days = (0..ChronoUnit.DAYS.between(
            LocalDate.parse(planStart, dateFormatter), LocalDate.parse(planEnd, dateFormatter)
        ).toInt()).toList()
        dates = days.map {
            LocalDate.parse(planStart).plusDays(it.toLong())
        }
        dayOfWeek = dates.map { it.dayOfWeek.value }
        planEditViewModel.onStartTimeChange(1)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            verticalArrangement = Arrangement.spacedBy(6.dp, Alignment.Top)
        ) {
            //跳轉
            Button(onClick = { navController.navigate(PLAN_HOME_ROUTE) }) { }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .weight(0.2f)
            ) {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .weight(0.9f)
                ) {
                    items(days.size) {
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .border(1.dp, Color.Black)
                                .clickable {
                                    selectedDate = dates[it].format(dateFormatter)
                                    Log.d("d selectedDate", selectedDate)
                                    coroutineScope.run {
                                        launch {}
                                    }
                                },
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "${dates[it]}",
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(horizontal = 6.dp)
                            )
                            Text(
                                text = "第${days[it] + 1}天",
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(horizontal = 6.dp)
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .weight(0.1f)
                        .border(1.dp, Color.Black),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    IconButton(
                        onClick = {
                            var newSchEnd = LocalDate.parse(plan.schEnd, dateFormatter)
                            plan.schEnd = newSchEnd.plusDays(1).format(dateFormatter)
                            coroutineScope.run {
                                launch {
                                    var planResponse = RetrofitInstance.api.updatePlan(plan)
                                    planResponse?.let { planHomeViewModel.setPlan(it) }
                                }
                            }
                        }, modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.add_box),
                            contentDescription = "add Icon",
                            modifier = Modifier.size(30.dp),
                            tint = Color.Unspecified
                        )
                    }
                    IconButton(
                        onClick = {
                            var newSchEnd = LocalDate.parse(plan.schEnd, dateFormatter)
                            plan.schEnd = newSchEnd.minusDays(1).format(dateFormatter)
                            coroutineScope.run {
                                launch {
                                    var planResponse = RetrofitInstance.api.updatePlan(plan)
                                    planResponse?.let { planHomeViewModel.setPlan(it) }
                                }
                            }
                        }, modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.remove),
                            contentDescription = "remove Icon",
                            modifier = Modifier.size(30.dp),
                            tint = Color.Unspecified
                        )
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .weight(0.3f),
                verticalArrangement = Arrangement.spacedBy(
                    6.dp, Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = plan.schName, style = TextStyle(
                        fontSize = 24.sp
                    ), modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "${plan.schStart} ~ ${plan.schEnd}", style = TextStyle(
                        fontSize = 16.sp
                    ), modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "最後編輯時間: 2024-11-1", style = TextStyle(
                        fontSize = 16.sp
                    ), modifier = Modifier.fillMaxWidth()
                )
            }
            if (selectedDate.isNotEmpty()) {
                planEditViewModel.setDstsForDate(selectedDate)
                Log.d("d selectedDate", selectedDate)
                Row(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color.LightGray)
                        .border(1.dp, Color.Black)
                        .clickable { addDstBtAtTop = true },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.add_location),
                        contentDescription = "Add Icon",
                        modifier = Modifier.size(30.dp),
                        tint = Color.Unspecified
                    )
                    Text(
                        text = "新增景點", //變數
                        style = TextStyle(
                            fontSize = 16.sp, textAlign = TextAlign.Center
                        ), modifier = Modifier.padding(end = 6.dp)
                    )
                }
                LazyVerticalGrid(
                    columns = GridCells.Fixed(1), // 每列 1 個小卡
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.75f)
                ) {
                    Log.d("d lazyColumn size", "${dstsForDate.size}")
                    Log.d("d lazyColumn", "${dstsForDate}")
                    items(dstsForDate.size) { index ->
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            ShowDstRow(
                                dst = dstsForDate[index],
                                dstIndex = index,
                                onStartTimeChange = { startTime ->
                                    planEditViewModel.onStartTimeChange(mode = 1)
                                })
                            Spacer(modifier = Modifier.padding(0.dp))
                        }
                    }
                }
            }
        }
        if (addDstBtAtTop) {
            mainAddDstAlertDialog(onDismissRequest = { addDstBtAtTop = false },
                poiSelected = {
                    val newDst = Destination()
                    //拿到poi寫到dest
                    newDst.dstNo = if (dstsForDate.size > 0) dstsForDate.last().dstNo + 1 else 1
                    newDst.schNo = schNo
                    newDst.poiNo = it.poiNo
                    newDst.dstName = it.poiName
                    newDst.dstAddr = it.poiAdd
                    newDst.dstPic = ByteArray(0)
                    newDst.dstDep = "沒有敘述"
                    newDst.dstDate = selectedDate
                    newDst.dstStart = if (dstsForDate.size > 0) {
                        addMultipleTimeStrings(
                            dstsForDate.last().dstEnd, dstsForDate.last().dstInr
                        )
                    } else {
                        "00:00:00"
                    }
                    Log.d("newDst.dstStart", "${newDst.dstStart}")
                    newDst.dstEnd = addMultipleTimeStrings(newDst.dstStart, "01:00:00")
                    newDst.dstInr = "01:00:00"
                    Log.d("DstsForDate SIZE", "${planEditViewModel.dstsForDateState.value.size}")
                    planEditViewModel.addToDses(newDst)
                    planEditViewModel.addToDstForDate(newDst)
                    Log.d(
                        "DstsForDate size after", "${planEditViewModel.dstsForDateState.value.size}"
                    )
                    coroutineScope.run {
                        launch {
                            var dstResponse = requestVM.addDst(newDst)
                            dstResponse?.let {
                                Log.d("d dstResponse", "${dstResponse}")
                            }
                        }
                    }
                })
        }
    } else {
        planStart = ""
        planEnd = ""
        planLastEdit = ""
        days = emptyList()
        dates = emptyList()
        dayOfWeek = emptyList()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowDstRow(
    dst: Destination,
    dstIndex: Int,
    onStartTimeChange: (String) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    var addDstBtAtRows by remember { mutableStateOf(false) }
    var showStayTimeInputS by remember { mutableStateOf(false) }
    var showStayTimeInputE by remember { mutableStateOf(false) }
    var showTranserTimeInput by remember { mutableStateOf(false) }
    var selectedStayHourS by remember { mutableStateOf(0) }
    var selectedStayMinuteS by remember { mutableStateOf(0) }
    var selectedStayHourE by remember { mutableStateOf(0) }
    var selectedStayMinuteE by remember { mutableStateOf(0) }
    var selectedTransferHour by remember { mutableStateOf(0) }
    var selectedTransferMinute by remember { mutableStateOf(0) }
    val HMregex = "\\d{2}:\\d{2}".toRegex()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .border(1.dp, Color.Black),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth(0.9f)
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
                    text = dst.dstName, fontSize = 20.sp, textAlign = TextAlign.Start
                )
                Text(
                    text = dst.dstAddr, fontSize = 16.sp, textAlign = TextAlign.Start
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
                onClick = { addDstBtAtRows = true }, modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.more_horiz),
                    contentDescription = "menu Icon",
                    modifier = Modifier.size(30.dp),
                    tint = Color.Unspecified
                )
            }
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .border(1.dp, Color.Black),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "預計開始: ${HMregex.find(dst.dstStart)?.value}",
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(8.dp)
                .border(1.dp, Color.Black)
                .clickable { showStayTimeInputS = true },
        )
        Spacer(modifier = Modifier.width(100.dp))
        Text(
            text = "預計結束: ${HMregex.find(dst.dstEnd)?.value}",
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(8.dp)
                .border(1.dp, Color.Black)
                .clickable { showStayTimeInputE = true },
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .border(1.dp, Color.Black)
            .clickable { showTranserTimeInput = true },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "預計轉移：${HMregex.find(dst.dstInr)?.value}",
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(8.dp)
        )
    }
    if (showStayTimeInputS) {
        ShowTimeInput(
            onDismiss = { showStayTimeInputS = false },
            onConfirm = {
                var isOverEnd = LocalTime.of(it.hour, it.minute) > LocalTime.of(
                    selectedStayHourE,
                    selectedStayMinuteE
                )
                if (!isOverEnd) {
                    Log.d("Time end set faild", "${it.hour}: ${it.minute} 超出結束時間")

                } else {
                    selectedStayHourS = it.hour
                    selectedStayMinuteS = it.minute
                    var formatH = String.format("%02d", selectedStayHourS)
                    var formatM = String.format("%02d", selectedStayMinuteS)
                    var concate = "${formatH}:${formatM}:00"
                    dst.dstStart = concate
                    coroutineScope.run {
                        launch {
                            var response = RetrofitInstance.api.updateDst(dst)
                            response?.let {
                                Log.d("d response", "${response}")
                            }
                        }
                    }
                    onStartTimeChange.invoke(concate)
                }
            }
        )
    }
    if (showStayTimeInputE) {
        ShowTimeInput(onDismiss = { showStayTimeInputE = false }, onConfirm = {
            var isOverStart = LocalTime.of(it.hour, it.minute) < LocalTime.of(
                selectedStayHourS,
                selectedStayMinuteS
            )
            if (!isOverStart) {
                Log.d("Time end set faild", "${it.hour}: ${it.minute} 超出開始時間")
            } else {
                selectedStayHourE = it.hour
                selectedStayMinuteE = it.minute
                var formatH = String.format("%02d", selectedStayHourE)
                var formatM = String.format("%02d", selectedStayMinuteE)
                var concate = "${formatH}:${formatM}:00"
                dst.dstEnd = concate
                coroutineScope.run {
                    launch {
                        var response = RetrofitInstance.api.updateDst(dst)
                        response?.let {
                            Log.d("d response", "${response}")
                        }
                    }
                }
            }
        })
    }
    if (showTranserTimeInput) {
        ShowTimeInput(onDismiss = { showTranserTimeInput = false }, onConfirm = {
            selectedTransferHour = it.hour
            selectedTransferMinute = it.minute
            var formatH = String.format("%02d", selectedTransferHour)
            var formatM = String.format("%02d", selectedTransferMinute)
            var concate = "${formatH}:${formatM}:00"
            dst.dstInr = concate
            coroutineScope.run {
                launch {
                    var response = RetrofitInstance.api.updateDst(dst)
                    response?.let {
                        Log.d("d response", "${response}")
                    }
                }
            }
        })
    }
    if (selectedStayHourS > 0) {
        Log.d("d selectedStayHourS", "${selectedStayHourS}")
    }
    if (addDstBtAtRows) {
        addDstAlertDialogByRows(onDismissRequest = { addDstBtAtRows = false })
    }
}


@Composable
fun addDstAlertDialogByRows(
    onDismissRequest: () -> Unit,
) {
    AlertDialog(onDismissRequest = onDismissRequest, title = {}, text = {
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
    }, confirmButton = {})
}

@Composable
fun mainAddDstAlertDialog(
    onDismissRequest: () -> Unit, poiSelected: (Poi) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    var showDialog by remember { mutableStateOf(false) }
    AlertDialog(onDismissRequest = onDismissRequest, title = {}, text = {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showDialog = true },
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
    }, confirmButton = {})
    if (showDialog) {
        // 呼叫 SelectableGridDialog
        SelectableGridDialog(onItemClick = { selectedItem ->
            showDialog = false // 點擊後關閉對話框
            onDismissRequest()
            poiSelected(selectedItem)
        }, onDismiss = {
            showDialog = false // 點擊關閉按鈕或背景
        })
    }
}

@Composable
fun SelectableGridDialog(
    onItemClick: (Poi) -> Unit, // 點擊事件回調
    onDismiss: () -> Unit // 關閉對話框
) {
    val coroutineScope = rememberCoroutineScope()
    var items by remember { mutableStateOf(emptyList<Poi>()) }
    coroutineScope.run {
        launch {
            var response = RetrofitInstance.api.getPois()
            response?.let {
                items = it
                Log.d("d items", "message: ${items}")
            }
        }
    }
    AlertDialog(onDismissRequest = onDismiss, title = {
        Text(text = "選擇一個項目")
    }, text = {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 400.dp) // 設置對話框的高度上限
        ) {
            items(items.size) { index ->
                val item = items[index]
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable { onItemClick(item) }, contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${item.poiNo}:  ${item.poiName}",
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }, confirmButton = {
        TextButton(onClick = onDismiss) {
            Text("關閉")
        }
    })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowTimeInput(
    onConfirm: (TimePickerState) -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(onDismissRequest = onDismiss, title = {}, text = {
        val currentTime = Calendar.getInstance()

        val timePickerState = rememberTimePickerState(
            initialHour = currentTime.get(Calendar.HOUR_OF_DAY),
            initialMinute = currentTime.get(Calendar.MINUTE),
            is24Hour = true,
        )

        Column {
            TimeInput(state = timePickerState)
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Button(onClick = onDismiss) {
                    Text("取消")
                }
                Spacer(modifier = Modifier)
                Button(onClick = {
                    onConfirm(timePickerState)
                    onDismiss()
                }) {
                    Text("確定")
                }
            }
        }
    }, confirmButton = {})
}

fun addMultipleTimeStrings(vararg times: String): String {
    // 將所有時間字串轉換為總分鐘數並相加
    val totalMinutes = times.sumOf { time ->
        val (hours, minutes) = time.split(":").map { it.toInt() }
        hours * 60 + minutes
    }

    // 轉換為小時和分鐘
    val resultHours = totalMinutes / 60
    val resultMinutes = totalMinutes % 60

    // 格式化成 "HH:mm"
    return String.format("%02d:%02d", resultHours, resultMinutes)
}


@Preview
@Composable
fun PreviewPlanEditScreen() {
    PlanEditScreen(
        rememberNavController(), viewModel(), viewModel(), requestVM = viewModel(), schNo = 2
    )
}

//@Composable
//fun ShowDayRow(
//    day: Int,
//    date: LocalDate,
//    dayOfWeek: String,
//    dsts: List<Destination>,
//    plan: Plan
//) {
//    //CoroutineScope
//    val coroutineScope = rememberCoroutineScope()
//    //測試用poi
//    var poi by remember { mutableStateOf(Poi()) }
//    //加入景點按鈕
//    var addDstBtAtTop by remember { mutableStateOf(false) }
//    //日期轉換
//    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
//    val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(Color.LightGray)
//            .border(1.dp, Color.Black),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Text(
//            text = "第${day}天",
//            maxLines = 1,
//            fontSize = 20.sp,
//            textAlign = TextAlign.Center,
//            modifier = Modifier
//                .padding(6.dp)
//                .background(Color.White)
//        )
//        Text(
//            text = "${date} 星期${dayOfWeek}",
//            maxLines = 1,
//            fontSize = 20.sp,
//            textAlign = TextAlign.Center,
//            modifier = Modifier
//                .padding(6.dp)
//                .background(Color.White)
//        )
//    }
//    dsts.forEachIndexed { index, dst ->
//        if (plan.schNo != 0 && dst.dstDate == date.toString()) {
//            ShowDstRow(dst = dst)
//        }
//    }
//    //新增景點、新增天數
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(bottom = 10.dp)
//            .background(Color.LightGray),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally)
//    ) {
//        Row(
//            modifier = Modifier
//                .padding(4.dp)
//                .clip(RoundedCornerShape(8.dp))
//                .background(Color.White),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            IconButton(
//                onClick = { addDstBtAtTop = true },
//                modifier = Modifier.size(32.dp)
//            ) {
//                Icon(
//                    painter = painterResource(id = R.drawable.add_location),
//                    contentDescription = "Add Icon",
//                    modifier = Modifier.size(30.dp),
//                    tint = Color.Unspecified
//                )
//            }
//            Text(
//                text = "新增景點", //變數
//                style = TextStyle(
//                    fontSize = 16.sp,
//                    textAlign = TextAlign.Center
//                ),
//                modifier = Modifier.padding(end = 6.dp)
//            )
//        }
//        Spacer(Modifier.weight(1f))
//        Row(
//            modifier = Modifier
//                .padding(4.dp)
//                .clip(RoundedCornerShape(8.dp))
//                .background(Color.White),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            IconButton(
//                onClick = {},
//                modifier = Modifier.size(32.dp)
//            ) {
//                Icon(
//                    painter = painterResource(id = R.drawable.add_box),
//                    contentDescription = "Add Icon",
//                    modifier = Modifier.size(30.dp),
//                    tint = Color.Unspecified
//                )
//            }
//            Text(
//                text = "新增間隔時間", //變數
//                style = TextStyle(
//                    fontSize = 16.sp,
//                    textAlign = TextAlign.Center
//                ),
//                modifier = Modifier.padding(end = 6.dp)
//            )
//        }
//    }
//    if (addDstBtAtTop) {
//        mainAddDstAlertDialog(
//            onDismissRequest = { addDstBtAtTop = false },
//            poiSelected = {
//                poi = it
//                Log.d("poi no", "message: ${poi}")
//            }
//        )
//    }
//}


//poiSelected = { selected ->
//    poi = selected
//    val newDst = Destination()
//    coroutineScope.launch {
//        val setTime = async {
//            var dstResponse = requestVM.GetLastDst()
//            dstResponse?.let {
//                newDst.dstDep = "沒有敘述"
//                newDst.dstDate = selectedDate
//                newDst.dstStart = if(dstsForDate.size > 0) {
//                    addMultipleTimeStrings(
//                        dstsForDate.last().dstEnd,
//                        dstsForDate.last().dstInr
//                    )
//                } else {
//                    "00:00:00"
//                }
//                newDst.dstEnd = addMultipleTimeStrings(newDst.dstStart, "01:00:00")
//                newDst.dstInr = "00:00:00"
//                Log.d("newDst", "${newDst.dstStart}")
//            }
//        }
//        val setInfo = async {
//            newDst.dstNo = 0
//            newDst.schNo = schNo
//            newDst.poiNo = poi.poiNo
//            newDst.dstName = poi.poiName
//            newDst.dstAddr = poi.poiAdd
//            newDst.dstPic = ByteArray(0)
//        }
//        setTime.await()
//        setInfo.await()
//        launch {
//            //拿到poi寫到dest
//            var dstResponse = requestVM.AddDst(newDst)
//            Log.d("dstResponse", "${dstResponse}")
//            planEditViewModel.addToDses(newDst)
//            planEditViewModel.addToDstForDate(newDst)
//            Log.d("DstsForDate", "${planEditViewModel.dstsForDateState.value}")
//            Log.d("DstsForDate", "${planEditViewModel.dstsState.value}")
//        }
//    }
//}