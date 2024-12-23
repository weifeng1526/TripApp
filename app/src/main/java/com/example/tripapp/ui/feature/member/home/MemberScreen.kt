package com.example.tripapp.ui.feature.member.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tripapp.R

@Composable
fun MemberRoute() {
    MemberScreen()
}

@Preview
@Composable
fun PreviewMemberRoute() {
    MemberScreen()
}

@Composable
fun MemberScreen() {
//    val tabVisibility = TabsVM.tabVisibility.collectAsState()
    var tabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf(
        "景點收藏",
        "我的行李"
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight(0.07f),
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.End),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_person_add_24),
                    contentDescription = "image",
                    modifier = Modifier
                        .padding(end = 15.dp)
                        .size(40.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.25f)
                    .background(Color.LightGray),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 25.dp, bottom = 5.dp),
                    verticalArrangement = Arrangement.spacedBy(7.dp, Alignment.Bottom),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_foreground),
                        contentDescription = "image",
                        modifier = Modifier
                            .fillMaxHeight(0.7f)
                            .clip(CircleShape)
                    )
                    Text(
                        textAlign = TextAlign.Justify,
                        text = "會員登入",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .height(30.dp)
                            .padding()
                            .wrapContentSize(Alignment.Center)
                    )
                }
            }
        }
        HorizontalDivider(
            modifier = Modifier,
            color = Color.DarkGray
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
//            when (tabIndex) {
//                0 -> "TF"
//                1 -> "Bag"
//            }
//            if (tabVisibility.value) {
                TabRow(
                    selectedTabIndex = 2,
                    containerColor = colorResource(id = R.color.teal_200)
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            text = { Text(title) },
                            selected = index == tabIndex,
                            onClick = { tabIndex = index },
                            selectedContentColor = colorResource(id = R.color.purple_700),
                            unselectedContentColor = Color.Gray,
                        )
                    }
                }
//            }
        }
        Column {
            var testView by remember { mutableStateOf("") }
            Text(testView)
            testView = when (tabIndex) {
                0 -> "TF"
                1 -> "Bag"
                else -> ""
            }
        }

//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .weight(1f)
//        ) {
//
//        }
    }
}