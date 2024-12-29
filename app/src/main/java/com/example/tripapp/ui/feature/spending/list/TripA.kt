package com.example.tripapp.ui.feature.spending.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tripapp.R
import com.example.tripapp.ui.theme.*


@Preview
@Composable
fun tripAPre() {
    tripA(rememberNavController(), spendingListinfoVM = viewModel())
}

@Composable
fun tripA(
    navHostController: NavHostController,
    spendingListinfoVM: SpendingListViewModel = viewModel()
) {
    val spendingListStatus by spendingListinfoVM.spendingListInfo.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(white100)
    ) {
        Column(
            modifier = Modifier
                .background(white100)
                .padding(20.dp, 12.dp, 20.dp, 8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "消費明細",
                fontSize = 15.sp
            )

            spendingListStatus.forEach {
                spendingListStatus(
                    payByUserName = it.payByUserName,
                    className = it.className,
                    itemName = it.itemName,
                    dateTime = it.dateTime,
                    totalAmount = it.totalAmount,
                    perPersonAmount = it.perPersonAmount,
                    currency = it.currency,
                    numberOfPeople = it.numberOfPeople
                )
            }
        }


//copy------------------------------------------------------------------------
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(white100)
//        ) {
//            Column(
//                modifier = Modifier
//                    .background(white100)
//                    .padding(20.dp, 12.dp,20.dp, 8.dp)
//                    .fillMaxWidth(),
//                horizontalAlignment = Alignment.Start
//            ) {
//                Text(
//                    text = "消費明細",
//                    fontSize = 15.sp
//                )
//            }
//
//
//
//            Column(
//                modifier = Modifier
//                    .padding(20.dp, 0.dp),
//            ) {
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    Image(
//                        painter = painterResource(R.drawable.ic_member_01),
//                        contentDescription = "member icon",
//                        modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp)
//                    )
//                    Column(
//
//                    ) {
//                        Text(
//                            text = "Rubyyyyyer",
//                            fontSize = 16.sp,
//                            fontWeight = FontWeight.Bold
//                        )
//
//                        Row(
//                            modifier = Modifier.padding(0.dp, 4.dp),
//                        ) {
//                            Text(
//                                text = "交通",
//                                color = black600,
//                            )
//                            VerticalDivider(
//                                thickness = 2.dp,
//                                color = black500,
//                                modifier = Modifier
//                                    .padding(8.dp, 4.dp)
//                                    .height(14.dp)
//                            )
//                            Text(
//                                text = "高鐵車票",
//                                color = black600
//                            )
//                        }
//
//
//                        Text(
//                            text = "2025/12/25 19:25",
//                            color = black600
//                        )
//
//                    }
//                    Column() {
//                        Row(
//                            horizontalArrangement = Arrangement.End,
//                            modifier = Modifier
//                                .fillMaxWidth()
//                        ) {
//
//
//                            Column(
//                                horizontalAlignment = Alignment.End,
//
//
//                                ) {
//                                Text(
//                                    text = "5,000",
//                                    fontSize = 20.sp,
//                                    fontWeight = FontWeight.ExtraBold,
//                                    textAlign = TextAlign.End,
//                                    modifier = Modifier.padding(0.dp,0.dp,0.dp,4.dp)
//
//                                )
//                                Text(
//                                    text = "=1,000",
//                                    fontSize = 15.sp,
//                                    color = black600,
//                                    fontWeight = FontWeight.Bold,
//                                    textAlign = TextAlign.End
//
//                                )
//
//
//                            }
//
//
//                            Text(
//                                text = "JPY",
//                                fontSize = 14.sp,
//                                fontWeight = FontWeight.Bold,
//                                lineHeight = 24.sp,
//                                modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)
//                            )
//
//
//                        }
//                        Text(
//                            text = "Share People more +",
//                            fontSize = 14.sp,
//                            lineHeight = 24.sp,
//
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(0.dp,4.dp,0.dp,0.dp),
//                            textAlign = TextAlign.End,
//                        )
//                    }
//                }
//            }
//            HorizontalDivider(
//                thickness = 1.dp,
//                color = white400,
//                modifier = Modifier.padding(0.dp,12.dp)
//            )

//copy------------------------------------------------------------------------


    }
}


@Composable
fun spendingListStatus(
    payByUserName: String,
    className: String,
    itemName: String,
    dateTime: String,
    totalAmount: Double,
    perPersonAmount: Double,
    currency: String,
    numberOfPeople: Int,
) {

    Column(
        modifier = Modifier
            .padding(20.dp, 0.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_member_01),
                contentDescription = "member icon",
                modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp)
            )
            Column(

            ) {
                Text(
                    text = payByUserName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    modifier = Modifier.padding(0.dp, 4.dp),
                ) {
                    Text(
                        text = className,
                        color = black600,
                    )
                    VerticalDivider(
                        thickness = 2.dp,
                        color = black500,
                        modifier = Modifier
                            .padding(8.dp, 4.dp)
                            .height(14.dp)
                    )
                    Text(
                        text = itemName,
                        color = black600
                    )
                }


                Text(
                    text = dateTime,
                    color = black600
                )

            }
            Column() {
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {


                    Column(
                        horizontalAlignment = Alignment.End,


                        ) {
                        Text(
                            text = totalAmount.toString(),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            textAlign = TextAlign.End,
                            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 4.dp)

                        )
                        Text(
                            text = "=${perPersonAmount}",
                            fontSize = 15.sp,
                            color = black600,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.End

                        )


                    }


                    Text(
                        text = currency,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 24.sp,
                        modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)
                    )


                }
                Text(
                    text = numberOfPeople.toString(),
                    fontSize = 14.sp,
                    lineHeight = 24.sp,

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 4.dp, 0.dp, 0.dp),
                    textAlign = TextAlign.End,
                )
            }
        }
    }
    HorizontalDivider(
        thickness = 1.dp,
        color = white400,
        modifier = Modifier.padding(0.dp, 12.dp)
    )

}

@Composable
fun getList(
    list: List<SpendingRecord>,
    onClick: (SpendingRecord) -> Unit
) {
//    LazyColumn {
//        items(list) { listItem ->
//            ListItem(
//                trailingContent = { Icon() },
//
//                )
//        }
//    }
}
