package com.example.tripapp.ui.feature.spending.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tripapp.R
import com.example.tripapp.ui.theme.*

enum class tabsTrip {
    tripA,
    tripB,
    tripC

}



@Composable
fun SpendingRoute() {
    SpendingListScreen()
}

@Preview
@Composable
fun PreviewSpendingRoute() {
    SpendingListScreen()
}

@Composable
fun SpendingListScreen() {
    var tabsTripListIndex by remember { mutableIntStateOf(0) }
    val tabsTripList = listOf(
        tabsTrip.tripA,
        tabsTrip.tripB,
        tabsTrip.tripC
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(white100)

    ) {
        Column(
            modifier = Modifier
                .padding(32.dp, 20.dp),
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    text = "Hi,Rubyyyyyer ",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,

                    )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = white100,
                            contentColor = purple300
                        ),
                        border = BorderStroke(
                            2.dp, white400,
                        ),
                        modifier = Modifier.padding(0.dp, 0.dp, 8.dp, 0.dp)
                    ) {
                        Text(
                            text = "結算",
                            fontSize = 15.sp
                        )
                    }
                    Button(
                        onClick = { },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = white100,
                            contentColor = purple300
                        ),
                        border = BorderStroke(
                            2.dp, Color(0xFFDFDCEF),
                        )
                    ) {
                        Image(
                            painter = painterResource(R.drawable.ic_more),
                            contentDescription = "more",
                            Modifier.size(16.dp, 12.dp)
                        )
                    }
                }


            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 18.dp, 0.dp, 0.dp),

                ) {
                Row(

                    horizontalArrangement = Arrangement.Start

                ) {
                    Text(
                        text = "團體花費",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Start,
                        lineHeight = 24.sp,
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    Text(
                        text = "10,000",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.End,
                    )
                    Text(
                        text = "JPY",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 25.sp,
                        modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)
                    )
                }


            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 12.dp, 0.dp, 0.dp),

                ) {
                Row(

                    horizontalArrangement = Arrangement.Start

                ) {
                    Text(
                        text = "個人花費",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Start,
                        lineHeight = 24.sp,
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    Text(
                        text = "2,000",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.End,
                    )
                    Text(
                        text = "JPY",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 25.sp,
                        modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)
                    )
                }


            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 12.dp, 0.dp, 0.dp),

                ) {
                Row(

                    horizontalArrangement = Arrangement.Start

                ) {
                    Text(
                        text = "公費餘額",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        textAlign = TextAlign.Start,
                        lineHeight = 24.sp,
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {

                    Text(
                        text = "200,000",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold,
                        textAlign = TextAlign.End,
                    )
                    Text(
                        text = "JPY",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        lineHeight = 25.sp,
                        modifier = Modifier.padding(10.dp, 0.dp, 0.dp, 0.dp)
                    )
                }


            }

        }

        HorizontalDivider(thickness = 2.dp, color = white400)

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {


            TabRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(white100),
                selectedTabIndex = tabsTripListIndex,
                indicator = { tabPositions ->
//                    SecondaryIndicator(
//                        modifier = Modifier.tabIndicatorOffset(tabPositions[tabsTripListIndex]),
//                        color = Color.Green, // 修改指標顏色為綠色
//                    )


                },
                divider = {
//                    HorizontalDivider(
//                        color = Color.Blue // 修改分隔線顏色為淺灰色
//                    )
                },
                containerColor = white300
            ) {

                tabsTripList.forEachIndexed { index: Int, screen: tabsTrip ->
                    Tab(
                        text = {
                            Text(
                                text = screen.toString(),
                                fontSize = 15.sp
                            )
                        },
                        selected = index == tabsTripListIndex,
                        selectedContentColor = black900,
                        unselectedContentColor = black700,
                        onClick = { tabsTripListIndex = index }
                    )
                }


            }


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .padding(0.dp, 12.dp, 0.dp, 0.dp)
            ) {
                when (tabsTripListIndex) {
                    0 -> tripA()
                    1 -> tripB()
                    2 -> tripC()
                }
            }
        }
    }
}


