package com.example.tripapp.ui.feature.member.home.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun notifyRoute() { }

@Composable
fun HomeTFScreen(
    navController: NavHostController = rememberNavController(),
    tabsVM: TabsVM
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,


        ) {
        Text(
            text = "我的行李 ",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun HomeBagRoute() { }

@Composable
fun HomeBagScreen(
    navController: NavHostController = rememberNavController(),
    tabsVM: TabsVM
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,


        ) {
        Text(
            text = "景點收藏 ",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview
@Composable
fun PreviewTabsScreenRoute() {

}
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun HomeTF(
//    navController: NavHostController = rememberNavController(),
//    tabsVM: TabsVM
//) {
//    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
//
//    Scaffold(
//        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
//    ) { }
//
//    @OptIn(ExperimentalMaterial3Api::class)
//    @Composable
//    fun HomeBag(tabVM: Any) {
//
//    }
//}
