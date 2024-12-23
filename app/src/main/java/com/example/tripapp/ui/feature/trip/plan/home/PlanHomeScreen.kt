package com.example.swithscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tripapp.R
import com.example.tripapp.ui.feature.trip.plan.alter.PLAN_Alter_ROUTE
import com.example.tripapp.ui.feature.trip.plan.create.PLAN_CREATE_ROUTE
import com.example.tripapp.ui.feature.trip.plan.edit.PLAN_EDIT_ROUTE
import com.example.tripapp.ui.feature.trip.plan.home.Plan
import com.example.tripapp.ui.feature.trip.plan.home.PlanHomeViewModel

@Composable
fun PlanHomeScreen(
    navController: NavController,
    planHomeViewModel: PlanHomeViewModel = viewModel()
) {
    var vmSize by remember { mutableIntStateOf(planHomeViewModel.plansState.value.size) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 6.dp, vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .background(Color.White),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {},
                    modifier = Modifier.size(50.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.article),
                        contentDescription = "schedule Icon",
                        modifier = Modifier.size(48.dp),
                        tint = Color.Unspecified
                    )
                }
                Text(
                    text = "行程表 x ${vmSize}", //變數
                    style = TextStyle(
                        fontSize = 20.sp,
                        textAlign = TextAlign.Center
                    ),
                    modifier = Modifier.padding(end = 6.dp)
                )
            }
            Spacer(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 5.dp)
            ) // 中間空白，讓其他內容排在最右邊
            IconButton(
                onClick = { navController.navigate(PLAN_CREATE_ROUTE) },
                modifier = Modifier
                    .size(50.dp)
                    .background(Color.White)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.note_add),
                    contentDescription = "Add Icon",
                    modifier = Modifier.size(48.dp),
                    tint = Color.Unspecified
                )
            }
            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(50.dp)
                    .background(Color.White)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.list_sort),
                    contentDescription = "sort Icon",
                    modifier = Modifier.size(48.dp),
                    tint = Color.Unspecified
                )
            }
        }
        LazyVerticalGrid(
            columns = GridCells.Fixed(1), // 每列 1 個小卡
            modifier = Modifier.fillMaxSize()
        ) {
            items(planHomeViewModel.plansState.value.size) { index ->
                var plan = planHomeViewModel.plansState.value[index]
                ShowPlanCard(
                    plan = plan,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun ShowPlanCard(
    plan: Plan,
    navController: NavController
) {
    var expandAlertDialog by remember { mutableStateOf(false) }
    //行程表
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .height(320.dp)
            .background(Color.LightGray)
            .clickable { navController.navigate("${PLAN_EDIT_ROUTE}/${plan.schNo}") }
    ) {
        Image(
            painter = painterResource(R.drawable.dst),//預設圖
            contentDescription = "",
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
                        text = plan.schName, //行程名稱
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
                        text = "${plan.schStart} ~ ${plan.schEnd}", //開始日期~結束日期
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
                        onClick = { expandAlertDialog = true },
                        modifier = Modifier.size(50.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.more_horiz),
                            contentDescription = "menu Icon",
                            modifier = Modifier.size(48.dp),
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
                            painter = painterResource(id = R.drawable.group),
                            contentDescription = "friends Icon",
                            modifier = Modifier.size(50.dp),
                            tint = Color.Unspecified
                        )
                    }
                }
            }
        }
    }
    ShowPlanConfigsDialog(
        plan = plan,
        expand = expandAlertDialog,
        onDismiss = { expandAlertDialog = false },
        navController = navController
    )
}

@Composable
fun ShowPlanConfigsDialog(
    plan: Plan,
    expand: Boolean,
    onDismiss: () -> Unit,
    navController: NavController
) {
    if(expand) {
        AlertDialog(
            title = { },
            shape = RectangleShape,
            onDismissRequest = onDismiss,
            text = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(onClick = { navController.navigate(PLAN_Alter_ROUTE) }) {
                        Text("變更行程設定")
                    }
                    Button(onClick = {}) {
                        Text("複製行程表")
                    }
                    Button(onClick = {}) {
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
}


@Preview
@Composable
fun PreviewPlanHomeScreen() {
    PlanHomeScreen(
        rememberNavController()
    )
}