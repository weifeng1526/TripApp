package com.example.swithscreen

//import com.example.tripapp.ui.feature.trip.plan.home.Plan
//import com.example.tripapp.ui.feature.trip.plan.restful.CreatePlan
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tripapp.R
import com.example.tripapp.ui.feature.member.GetUid
import com.example.tripapp.ui.feature.member.MemberRepository
import com.example.tripapp.ui.feature.trip.dataObjects.PlanInfo
import com.example.tripapp.ui.feature.trip.dataObjects.convertLongToDate
import com.example.tripapp.ui.feature.trip.dataObjects.convertLongToDateTime
import com.example.tripapp.ui.feature.trip.plan.create.genPlanCreateNavigationRoute
import com.example.tripapp.ui.feature.trip.plan.crew.PLAN_CREW_ROUTE
import com.example.tripapp.ui.feature.trip.plan.edit.PLAN_EDIT_ROUTE
import com.example.tripapp.ui.feature.trip.plan.home.PlanHomeViewModel
import com.example.tripapp.ui.theme.black900
import com.example.tripapp.ui.theme.purple200
import com.example.tripapp.ui.theme.purple300
import com.example.tripapp.ui.theme.white100
import com.example.tripapp.ui.theme.white400

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlanHomeScreen(
    navController: NavController,
    planHomeViewModel: PlanHomeViewModel
) {
    val uid = GetUid(MemberRepository)
    val density = LocalDensity.current
    val widthDp = LocalConfiguration.current.screenWidthDp
    val heightDp = LocalConfiguration.current.screenHeightDp
    Log.d("sWidthDp", widthDp.toString())
    Log.d("sHeightDp", heightDp.toString())
    val scrollPosition by planHomeViewModel.scrollPosition.collectAsState()
    val plansInfo by planHomeViewModel.plansInfo.collectAsState()
    val offset by planHomeViewModel.offset.collectAsState()
    val limit = planHomeViewModel.limit
    var page by remember { mutableStateOf(0) }
    var selectedPlanId by remember { mutableIntStateOf(0) }
    var titleName = listOf("已創建的行程", "已加入的行程")
    var selectedTitle by remember { mutableStateOf(titleName[0]) }
    var expandPlanConfigDialog by remember { mutableStateOf(false) }
    //如果可視部分受到變化，不會影響狀態並重組
    var lazyGridState = rememberLazyGridState(scrollPosition)

    LaunchedEffect(lazyGridState) {
        snapshotFlow { lazyGridState.layoutInfo.visibleItemsInfo }
            .collect { visibleItems ->
                if (visibleItems.isNotEmpty()) {
                    planHomeViewModel.setScrollPosition(lazyGridState.firstVisibleItemIndex)
                    val lastVisibleItemIndex = visibleItems.last().index
                    println("最後一個可見項目的索引：$lastVisibleItemIndex")
                    if(lastVisibleItemIndex + 1 == offset) {
                        Log.d("inoffset", "$offset")
                        planHomeViewModel.fetchPlansInfo(uid, limit, offset)
                        page += 1
                        planHomeViewModel.setOffset(page * limit)
                    }
                }
            }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(white100)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(brush = Brush.verticalGradient(colors = listOf(white100, white400))),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { navController.navigate(genPlanCreateNavigationRoute()) },
                modifier = Modifier
                    .fillMaxWidth() // 按鈕寬度佔滿全螢幕
                    .padding(horizontal = 16.dp, vertical = 10.dp) // 可選：設定水平間距
                    .border(2.dp, purple300, shape = RoundedCornerShape(24.dp))
                    .height(56.dp), // 可選：設定固定高度
                shape = RoundedCornerShape(24.dp), // 設定圓角
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.white_100), // 設定背景顏色
                    contentColor = Color.Black, // 設定文字顏色
                    disabledContainerColor = colorResource(id = R.color.white_300), // 禁用狀態背景顏色
                    disabledContentColor = Color.LightGray // 禁用狀態文字顏色
                )
            ) {
                Text(text = "新增行程表", fontSize = 20.sp) // 按鈕文字與樣式
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp)) // 裁剪為圓角
                    .border(2.dp, purple300, shape = RoundedCornerShape(24.dp)) // 使用相同的圓角形狀
                    .weight(1f)
                    .background(
                        color = if (selectedTitle.equals(titleName[0])) colorResource(id = R.color.purple_200)
                        else colorResource(id = R.color.white_100),
                        shape = RoundedCornerShape(24.dp)
                    )
                    .clickable { selectedTitle = titleName[0] },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${titleName[0]}",
                    style = TextStyle(
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        color = if (selectedTitle.equals(titleName[0])) colorResource(id = R.color.white_100)
                        else colorResource(id = R.color.black_900),
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(24.dp)) // 裁剪為圓角
                    .border(2.dp, purple300, shape = RoundedCornerShape(24.dp)) // 使用相同的圓角形狀
                    .weight(1f)
                    .background(
                        color = if (selectedTitle.equals(titleName[1])) colorResource(id = R.color.purple_200)
                        else colorResource(id = R.color.white_100),
                        shape = RoundedCornerShape(24.dp)
                    )
                    .clickable { selectedTitle = titleName[1] },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${titleName[1]}",
                    style = TextStyle(
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center,
                        color = if (selectedTitle.equals(titleName[1])) colorResource(id = R.color.white_100)
                        else colorResource(id = R.color.black_900),
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }

        if (plansInfo.size > 0) {
            LazyVerticalGrid(
                state = lazyGridState,
                columns = GridCells.Fixed(1), // 每列 1 個小卡
                modifier = Modifier.fillMaxSize()
            ) {
                //所有plan
                items(plansInfo.size) { index ->
                    var plan = plansInfo[index]
                    val startDate = plan.schStart?.let { convertLongToDate(it).toString() }?: ""
                    val endDate = plan.schEnd?.let { convertLongToDate(it).toString() }?: ""
                    val lastEditTime = plan.schLastEdit?.let { convertLongToDateTime(it).toString() }?: ""
                    //行程表
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .height(300.dp)
                            .border(1.dp, purple300, shape = RoundedCornerShape(8.dp)),
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = purple300,
                            contentColor = purple300
                        )
                    ) {
                        Image(
                            painter = painterResource(R.drawable.aaa),
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .padding(start = 6.dp, end = 6.dp, top = 6.dp)
                                .fillMaxWidth()
                                .height(200.dp)
                                .clickable {
                                    navController.navigate("${PLAN_EDIT_ROUTE}/${plan.schNo}")
                                }
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(bottom = 6.dp, start = 6.dp, end = 6.dp)
                                .background(white100)
                        ) {
                            Column(
                                horizontalAlignment = Alignment.Start,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .fillMaxWidth(0.8f)
                                    .fillMaxHeight()
                                    .padding()
                                    .clip(RoundedCornerShape(8.dp)) // 裁剪為圓角
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(0.6f)
                                ) {
                                    Text(
                                        text = "${plan.schName}", //開始日期~結束日期
                                        maxLines = 1,
                                        fontSize = 24.sp,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center,
                                        color = black900,
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
                                        text = "${startDate} ~ ${endDate}", //開始日期~結束日期
                                        maxLines = 1,
                                        fontSize = 16.sp,
                                        color = black900,
                                        modifier = Modifier.padding(start = 18.dp)
                                    )
                                }
                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .padding(5.dp),
                                verticalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterVertically),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                if (selectedTitle.equals(titleName[0])) {
                                    Row(
                                        horizontalArrangement = Arrangement.End,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .fillMaxHeight(0.5f)
                                            .padding(end = 6.dp)
                                    ) {
                                        IconButton(
                                            onClick = {
                                                expandPlanConfigDialog = true
                                                selectedPlanId = plan.schNo
                                                plan.schState = 0
                                            },
                                            modifier = Modifier.size(40.dp)
                                        ) {

                                            Icon(
                                                painter = painterResource(id = R.drawable.more_horiz),
                                                contentDescription = "more Icon",
                                                modifier = Modifier
                                                    .size(40.dp)
                                                    .background(purple200)
                                                    .padding(5.dp),
                                                tint = white100
                                            )
                                        }
                                    }
                                }
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.End,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight()
                                        .padding(end = 6.dp)
                                ) {
                                    IconButton(
                                        onClick = { navController.navigate("${PLAN_CREW_ROUTE}/${plan.schNo}/${plan.schName}") },
                                        modifier = Modifier
                                            .size(40.dp)
                                    ) {
                                        Icon(
                                            painter = painterResource(id = R.drawable.group),
                                            contentDescription = "group Icon",
                                            modifier = Modifier
                                                .size(40.dp)
                                                .background(purple200)
                                                .padding(5.dp),
                                            tint = white100
                                        )
                                    }
                                }
                            }
                        }
                    }

                }
            }
        }
        if (expandPlanConfigDialog) {
            ShowPlanConfigsDialog(
                planInfo = plansInfo.first { it.schNo == selectedPlanId },
                onDismiss = { expandPlanConfigDialog = false },
                navController = navController,
                planHomeViewModel = planHomeViewModel
            )
        }
    }
}


@Composable
fun ShowPlanConfigsDialog(
    planInfo: PlanInfo,
    onDismiss: () -> Unit,
    navController: NavController,
    planHomeViewModel: PlanHomeViewModel
) {
    AlertDialog(
        title = { },
        shape = RectangleShape,
        onDismissRequest = onDismiss,
        containerColor = white100,
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Button(onClick = {

                }) {
                    Text("刪除行程表")
                }
                Button(onClick = onDismiss) {
                    Text("返回")
                }
            }
        },
        confirmButton = {}
    )
}

@Preview
@Composable
fun PreviewPlanHomeScreen() {
    PlanHomeScreen(
        navController = rememberNavController(),
        planHomeViewModel = viewModel()
    )
}