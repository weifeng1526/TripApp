package com.example.tripapp.ui.feature.member.home

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
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tripapp.R
import com.example.tripapp.ui.feature.member.turfav.TUR_FAV_ROUTE
import com.example.tripapp.ui.theme.black100
import com.example.tripapp.ui.theme.black900
import com.example.tripapp.ui.theme.purple100
import com.example.tripapp.ui.theme.purple300
import com.example.tripapp.ui.theme.white100
import com.example.tripapp.ui.theme.white300

@Composable
fun MemberRoute(navController: NavHostController) {
    MemberScreen(
        onTurFavClick = { navController.navigate(TUR_FAV_ROUTE) }
    )
}

@Preview
@Composable
fun PreviewMemberRoute() {
    MemberScreen()
}

@Composable
fun MemberScreen(
    onTurFavClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(white100),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
                .background(white100),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.End,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxHeight(0.05f),
                horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.End),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.member_friends_baseline_group_24),
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
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(white100, white300)
                        ),
                    ),
//                Brush.linearGradient(listOf(black100, purple100))
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    modifier = Modifier
                        .padding(top = 24.dp, bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(7.dp, Alignment.Bottom),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_member),
                        contentDescription = "image",
                        modifier = Modifier
                            .fillMaxHeight(0.5f)
                            .size(60.dp)
                            .clip(CircleShape)
                    )
                    Text(
                        textAlign = TextAlign.Justify,
                        text = "會員登入",
                        fontSize = 18.sp,
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
            color = black900
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                HomeList(
                    onTurFavClick = onTurFavClick
                )
            }
        }
    }
}

@Composable
fun HomeList(onTurFavClick: () -> Unit) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable(onClick = onTurFavClick)
                .padding(top = 10.dp, bottom = 10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.myicon_suitcase_1),
                contentDescription = "image",
                modifier = Modifier
//                    .fillMaxHeight()
                    .size(125.dp) //調整Image比例
            )
            Text(text = "景點收藏")
        }

        HorizontalDivider(
            modifier = Modifier,
            color = black900
        )

        Row (
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable(onClick = onTurFavClick)
                .padding(top = 10.dp, bottom = 10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.myicon_suitcase_1),
                contentDescription = "image",
                modifier = Modifier
//                    .fillMaxHeight(0.3f)
                    .size(125.dp) //調整Image比例
            )
            Text(text = "我的行李")
        }
    }
}