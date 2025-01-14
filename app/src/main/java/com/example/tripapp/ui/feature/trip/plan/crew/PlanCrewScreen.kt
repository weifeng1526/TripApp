package com.example.tripapp.ui.feature.trip.plan.crew

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.swithscreen.PlanHomeScreen
import com.example.tripapp.R
import com.example.tripapp.ui.feature.member.GetUid
import com.example.tripapp.ui.feature.member.MemberRepository
import com.example.tripapp.ui.feature.trip.dataObjects.CrewMmeber
import com.example.tripapp.ui.feature.trip.plan.home.PLAN_HOME_ROUTE
import com.example.tripapp.ui.theme.purple100
import com.example.tripapp.ui.theme.purple300
import com.example.tripapp.ui.theme.purple500
import com.example.tripapp.ui.theme.white100
import com.example.tripapp.ui.theme.white300
import com.example.tripapp.ui.theme.white400

@Composable
fun PlanCrewScreen(
    navController: NavController,
    planCrewViewModel: PlanCrewViewModel,
    schNo: Int,
    schName: String
) {
    val crewOfMembers by planCrewViewModel.crewOfMembersSatate.collectAsState()
    LaunchedEffect(Unit) {
        planCrewViewModel.getCrewMembersRequest(schNo) {
            planCrewViewModel.setCrewMembers(it)
            Log.d("tag_PlanCrewScreen", "${it}")
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
                .padding(bottom = 0.dp)
                .background(white300),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .padding(start = 20.dp, end = 10.dp, top = 10.dp, bottom = 10.dp)
                    .border(1.dp, purple100, RoundedCornerShape(8.dp))
                    .wrapContentSize()
                    .clip(RoundedCornerShape(8.dp)),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.group),
                    contentDescription = "schedule Icon",
                    modifier = Modifier
                        .size(48.dp)
                        .padding(horizontal = 6.dp),
                    tint = purple500
                )
                Text(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .padding(bottom = 3.dp),
                    fontSize = 20.sp,
                    text = crewOfMembers.firstOrNull {
                        it.crewIde.toInt() == 2
                    }?.crewName ?: ""
                )
            }
            var crewMembersMap = crewOfMembers.associate { it.memNo to it.crewIde.toInt() }
            Log.d("test", "$crewMembersMap")
            if (crewMembersMap.get(GetUid(MemberRepository)) == 2) {
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .border(1.dp, purple100, RoundedCornerShape(8.dp))
                        .wrapContentSize()
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { },
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(
                        modifier = Modifier
                            .size(44.dp)
                            .padding(horizontal = 6.dp),
                        onClick = { navController.navigate("${MEMBER_INVITE_ROUTE}/${schNo}/${schName}") }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.person_add),
                            contentDescription = "",
                            modifier = Modifier.size(48.dp),
                            tint = purple500
                        )
                    }
                }
            }
        }

        LazyVerticalGrid(
            modifier = Modifier
                .wrapContentHeight(),
            columns = GridCells.Fixed(1)
        ) {
            items(crewOfMembers.size) {
                ShowPersonRow(
                    planCrewViewModel = planCrewViewModel,
                    crewMmeber = crewOfMembers[it]
                )
            }
        }
    }
}

@Composable
fun ShowPersonRow(
    planCrewViewModel: PlanCrewViewModel,
    crewMmeber: CrewMmeber
) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.wrapContentHeight()
        ) {
            ListItem(
                modifier = Modifier.background(white100),
                leadingContent = {
                    Icon(
                        painter = painterResource(id = R.drawable.person),
                        contentDescription = "",
                        modifier = Modifier.size(58.dp),
//                    tint = Color.Unspecified
                        contentColorFor(Color.Red)
                    )
                },
                headlineContent = {
                    Text(
                        text = crewMmeber.memName,
                        style = TextStyle(fontSize = 16.sp),
                        modifier = Modifier.padding(bottom = 2.dp)
                    )
                },
                supportingContent = {
                    Text(
                        text = crewMmeber.memEmail,
                        style = TextStyle(fontSize = 16.sp)
                    )
                },
                colors = ListItemDefaults.colors(
                    containerColor = white100
                ),
            )
            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(32.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.disabled),
                    contentDescription = "",
                    modifier = Modifier.size(30.dp),
                )
            }
        }
        Divider(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(2.dp),
            color = white400
        )
    }
}


@Preview
@Composable
fun PreviewPlanCrewScreen() {
    PlanCrewScreen(
        navController = rememberNavController(),
        planCrewViewModel = viewModel(),
        schNo = 1,
        schName = ""
    )
}