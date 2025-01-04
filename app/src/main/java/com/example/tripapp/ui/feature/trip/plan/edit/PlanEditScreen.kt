package com.example.tripapp.ui.feature.trip.plan

import android.app.AlertDialog
import android.app.TimePickerDialog
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tripapp.R
import com.example.tripapp.ui.feature.trip.plan.create.PLAN_CREATE_ROUTE
import com.example.tripapp.ui.feature.trip.plan.edit.PLAN_EDIT_ROUTE
import com.example.tripapp.ui.feature.trip.plan.edit.PlanEditViewModel
import com.example.tripapp.ui.feature.trip.plan.home.PLAN_HOME_ROUTE
import com.example.tripapp.ui.feature.trip.plan.home.PlanHomeViewModel
import com.example.tripapp.ui.restful.Destination
import com.example.tripapp.ui.restful.Plan
import com.example.tripapp.ui.restful.Poi
import com.example.tripapp.ui.restful.RequestVM
import com.ron.restdemo.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.sql.Time
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Calendar
import java.util.Formatter
import java.util.Locale
import kotlin.time.Duration

//理想是新增所有卡都可以隨時拖拉
@Composable
fun PlanEditScreen(
    navController: NavController,
    planEditViewModel: PlanEditViewModel,
    planHomeViewModel: PlanHomeViewModel,
    requestVM: RequestVM,
    schNo: Int
) {
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
            var dstsResponse: List<Destination> = emptyList()
            async {
                planResponse = requestVM.GetPlan(schNo)
                planResponse?.let {
                    planHomeViewModel.setPlan(it)
                }
            }.await()
            async {
                dstsResponse = requestVM.GetDstsBySchedId(schNo)
                dstsResponse.let {
                    planEditViewModel.setDsts(it)
                }
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
            LocalDate.parse(planStart, dateFormatter),
            LocalDate.parse(planEnd, dateFormatter)
        ).toInt()).toList()
        dates = days.map {
            LocalDate.parse(planStart).plusDays(it.toLong())
        }
        dayOfWeek = dates.map { it.dayOfWeek.value }
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
                                        launch {
                                        }
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
                                    var planResponse = RetrofitInstance.api.UpdatePlan(plan)
                                    planHomeViewModel.setPlan(planResponse)
                                }
                            }
                        },
                        modifier = Modifier.size(32.dp)
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
                                    var planResponse = RetrofitInstance.api.UpdatePlan(plan)
                                    planHomeViewModel.setPlan(planResponse)
                                }
                            }
                        },
                        modifier = Modifier.size(32.dp)
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
                    6.dp,
                    Alignment.CenterVertically
                ),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = plan.schName,
                    style = TextStyle(
                        fontSize = 24.sp
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    text = "${plan.schStart} ~ ${plan.schEnd}",
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
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.padding(end = 6.dp)
                    )
                    Text(
                        text = "出發時間", //變數
                        style = TextStyle(
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center
                        ),
                        modifier = Modifier.padding(end = 6.dp)
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
                            ShowDstRow(dst = dstsForDate[index])
                            Spacer(modifier = Modifier.padding(0.dp))
                        }
                    }
                }
            }
        }
        if (addDstBtAtTop) {
            mainAddDstAlertDialog(
                onDismissRequest = { addDstBtAtTop = false },
                poiSelected = {
                    poi = it
                    val newDst = Destination()
                    newDst.dstNo = 0
                    newDst.schNo = schNo
                    newDst.poiNo = poi.poiNo
                    newDst.dstName = poi.poiName
                    newDst.dstAddr = poi.poiAdd
                    newDst.dstPic = ByteArray(0)
                    newDst.dstDep = "沒有敘述"
                    newDst.dstDate = selectedDate
                    newDst.dstStart = "${selectedDate} 00:00:00"
                    newDst.dstEnd = "${selectedDate} 00:00:00"
                    newDst.dstInr = "00:00:00"
                    coroutineScope.run {
                        launch {
                            //拿到poi寫到dest
                            var dstResponse = requestVM.AddDst(newDst)
                            Log.d("dstResponse", "${dstResponse}")
                        }
                        launch {
                            //get 當日行程的景點
                        }
                    }
//                    val startTimeStr = dstsForDate.lastOrNull()?.dstStart?.split(" ")?.get(1)
//                    val endTimeStr = dstsForDate.lastOrNull()?.dstEnd?.split(" ")?.get(1)
//                    val inrTimeStr = dstsForDate.lastOrNull()?.dstInr
//                    val start = startTimeStr?.let { LocalTime.parse(it) }
//                    val end = endTimeStr?.let { LocalTime.parse(it) }
//                    val inr = inrTimeStr?.let { LocalTime.parse(it) }
//                    Log.d("between", "${java.time.Duration.between(start, end)}")
//                    Log.d("startTimeStr", "${startTimeStr}")
//                    Log.d("endTimeStr", "${endTimeStr}")
//                    Log.d("inTimerStr", "${inrTimeStr}")
//                    Log.d("start", "${start}")
//                    Log.d("end", "${end}")
//                    Log.d("inr", "${inr}")
                    planEditViewModel.addToDses(newDst)
                    planEditViewModel.addToDstForDate(newDst)
                    Log.d("DstsForDate", "${planEditViewModel.dstsForDateState.value}")
                    Log.d("DstsForDate", "${planEditViewModel.dstsState.value}")
                }
            )
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowDstRow(dst: Destination) {
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
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(0.9f)
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
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .border(1.dp, Color.Black),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
//            LocalTime.of(selectedStayHour,selectedStayMinute)
//            text = "預計開始: ${HMregex.find(dst.dstStart.split(" ")[1])?.value}",
            text = "預計開始: ",
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(8.dp)
                .border(1.dp, Color.Black)
                .clickable { showStayTimeInputS = true },
        )
        Spacer(modifier = Modifier.width(100.dp))
        Text(
//            LocalTime.of(selectedStayHour,selectedStayMinute)
            text = "預計結束:",
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
            text = "預計轉移：${selectedTransferHour}小時${selectedTransferMinute}分鐘",
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(8.dp)
        )
    }
    if (showStayTimeInputS) {
        ShowTimeInput(
            onDismiss = { showStayTimeInputS = false },
            onConfirm = {
                selectedStayHourS = it.hour
                selectedStayMinuteS = it.minute
                var formatH= String.format("%02d", selectedStayHourS)
                var formatM = String.format("%02d", selectedStayMinuteS)
                var concate = "${formatH}:${formatM}:00"
                //replace datetime 的time
                dst.dstStart =  dst.dstStart.replace(
                    dst.dstStart.split(" ")[1],
                    concate
                )
                Log.d("d concate", "message: ${concate}")
            }
        )
    }
    if (showStayTimeInputE) {
        ShowTimeInput(
            onDismiss = { showStayTimeInputE = false },
            onConfirm = {
                selectedStayHourE = it.hour
                selectedStayMinuteE = it.minute
                var formatH = String.format("%02d", selectedStayHourE)
                var formatM = String.format("%02d", selectedStayMinuteE)
                var concate = "${formatH}:${formatM}:00"
                //replace datetime 的time
                dst.dstEnd =  dst.dstEnd.replace(
                    dst.dstEnd.split(" ")[1],
                    concate
                )
                Log.d("d concate", "message: ${concate}")
            }
        )
    }
    if (showTranserTimeInput) {
        ShowTimeInput(
            onDismiss = { showTranserTimeInput = false },
            onConfirm = {
                selectedTransferHour = it.hour
                selectedTransferMinute = it.minute
                var formatH = String.format("%02d", selectedTransferHour)
                var formatM = String.format("%02d", selectedTransferMinute)
                var concate = "${formatH}:${formatM}:00"
                //replace datetime 的time
                dst.dstEnd =  dst.dstEnd.replace(
                    dst.dstEnd.split(" ")[1],
                    concate
                )
                Log.d("d concate", "message: ${concate}")
            }
        )
    }
    if(selectedStayHourS > 0) {
        Log.d("d selectedStayHourS", "${selectedStayHourS}")
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
    onDismissRequest: () -> Unit,
    poiSelected: (Poi) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    var showDialog by remember { mutableStateOf(false) }
    var items by remember { mutableStateOf(emptyList<Poi>()) }

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
                        .clickable {
                            coroutineScope.launch {
                                var response = emptyList<Poi>()
                                async {
                                    response = RetrofitInstance.api.GetPois()
                                    response.let {
                                        items = it
                                        Log.d("d items", "message: ${items}")
                                        showDialog = true
                                    }
                                }
                            }
                        },
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
    if (showDialog) {
        // 呼叫 SelectableGridDialog
        SelectableGridDialog(
            items = items,
            onItemClick = { selectedItem ->
                showDialog = false // 點擊後關閉對話框
                onDismissRequest()
                poiSelected(selectedItem)
            },
            onDismiss = {
                showDialog = false // 點擊關閉按鈕或背景
            }
        )
    }
}

@Composable
fun SelectableGridDialog(
    items: List<Poi>, // 每個 Item 的數據
    onItemClick: (Poi) -> Unit, // 點擊事件回調
    onDismiss: () -> Unit // 關閉對話框
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = "選擇一個項目")
        },
        text = {
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
                            .clickable { onItemClick(item) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "${item.poiNo}:  ${item.poiName}",
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("關閉")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowTimeInput(
    onConfirm: (TimePickerState) -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {},
        text = {
            val currentTime = Calendar.getInstance()

            val timePickerState = rememberTimePickerState(
                initialHour = currentTime.get(Calendar.HOUR_OF_DAY) + 8,
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
        requestVM = viewModel(),
        schNo = 2
    )
}