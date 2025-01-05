package com.example.tripapp.ui.feature.spending.list

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
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
import com.example.tripapp.ui.feature.spending.SpendingRecordVM
import com.example.tripapp.ui.feature.spending.SpendingRecord
import com.example.tripapp.ui.feature.spending.addlist.SPENDING_ADD_ROUTE
import com.example.tripapp.ui.restful.RequestVM
import com.example.tripapp.ui.theme.*
import com.ron.restdemo.RetrofitInstance


@Preview
@Composable
fun tripAPre() {
    tripA(rememberNavController() )
}



@Composable
fun tripA(
    navHostController: NavHostController,
    spendingRecordVM: SpendingRecordVM = viewModel(),
    spendingListStatus: List<SpendingRecord> = listOf()
) {
    //資料流，每一頁都可以動（新增修改），最後是把最新狀態撈出來。

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(white100)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth(),
            ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp, 24.dp, 20.dp, 16.dp),
                text = "消費明細",
                fontSize = 18.sp
            )

            Column {
                spendingListStatus.forEach {
                    spendingListStatusRow(it, navController = navHostController)
                }
            }


        }

    }


}


@Composable
fun spendingListStatusRow(
    spendingListStatus: SpendingRecord,
    navController: NavHostController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 0.dp)
            .clickable { navController.navigate(SPENDING_ADD_ROUTE) },

        ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_member_01),
                contentDescription = "member icon",
                modifier = Modifier
                    .padding(0.dp, 0.dp, 12.dp, 0.dp)
                    .size(56.dp)
            )
            Column(

            ) {
                Text(
                    //直接使用物件屬性
//                    text = RetrofitInstance.api.getSpendingList(),
                    //會員名稱
                    text = spendingListStatus.paidByName,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Row(
                    modifier = Modifier.padding(0.dp, 4.dp),
                ) {
                    Text(
                        //直接使用屬性，不是物件的，不要搞搞混
                        text = spendingListStatus.costType.toString(),
                        color = black600,
                        fontSize = 15.sp,
                    )
                    VerticalDivider(
                        thickness = 2.dp,
                        color = black500,
                        modifier = Modifier
                            .padding(8.dp, 4.dp)
                            .height(14.dp)
                    )
                    Text(
                        text = spendingListStatus.paidByName,
                        fontSize = 15.sp,
                        color = black600
                    )
                }


                Text(
                    text = spendingListStatus.paidByName,
                    fontSize = 15.sp,
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
                            text = spendingListStatus.paidByName,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            textAlign = TextAlign.End,
                            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 4.dp)

                        )
                        Text(
                            text = spendingListStatus.paidByName,
                            fontSize = 15.sp,
                            color = black600,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.End

                        )


                    }


                    Text(
                        text = spendingListStatus.paidByName,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 24.sp,
                        modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)
                    )


                }
                Text(
                    text = spendingListStatus.paidByName,
                    fontSize = 14.sp,
                    lineHeight = 24.sp,
                    color = black600,

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