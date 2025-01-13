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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.swithscreen.PlanHomeScreen
import com.example.tripapp.R
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
                .padding(bottom = 20.dp)
                .background(white100),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .border(1.dp, purple500, RoundedCornerShape(8.dp))
                    .wrapContentSize()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White),
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
                    modifier = Modifier.padding(horizontal = 10.dp),
                    text = crewOfMembers.firstOrNull {
                        it.crewIde.toInt() == 2
                    }?.crewName ?: ""
                )
            }
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .border(1.dp, purple500, RoundedCornerShape(8.dp))
                    .wrapContentSize()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.White)
                    .clickable { },
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(
                    modifier = Modifier
                        .size(48.dp)
                        .padding(horizontal = 6.dp)
                        .background(Color.White),
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
        LazyVerticalGrid(
            modifier = Modifier.fillMaxSize(),
            columns = GridCells.Fixed(1)
        ) {
            items(crewOfMembers.size) {
//                Divider(color = purple500, thickness = 1.dp)
                ShowPersonRow(
                    planCrewViewModel = planCrewViewModel,
                    crewMmeber = crewOfMembers[it]
                )
                HorizontalDivider(color = purple500, thickness = 1.dp)
            }
        }

    }
}

@Composable
fun ShowPersonRow(
    planCrewViewModel: PlanCrewViewModel,
    crewMmeber: CrewMmeber
) {
    Row(
        modifier = Modifier.wrapContentHeight()
    ) {
        ListItem(
            modifier = Modifier,
            leadingContent = {
                Icon(
                    painter = painterResource(id = R.drawable.person),
                    contentDescription = "",
                    modifier = Modifier.size(48.dp),
                    tint = Color.Unspecified
                )
            },
            headlineContent = {
                Text(
                    text = crewMmeber.memName
                )
            },
            supportingContent = {
                Text(text = crewMmeber.memEmail)
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
                tint = Color.Unspecified
            )
        }

    }
//    HorizontalDivider(color = purple500, thickness = 1.dp)

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