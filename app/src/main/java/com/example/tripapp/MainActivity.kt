package com.example.tripapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.tripapp.ui.feature.spending.addlist.SPENDING_ADD_ROUTE
import com.example.tripapp.ui.feature.spending.addlist.spendingAddRoute
import com.example.tripapp.ui.feature.spending.deposit.SPENDING_DEPOSIT_ROUTE
import com.example.tripapp.ui.feature.spending.deposit.spendingDepositRoute
import com.example.tripapp.ui.feature.spending.list.SPENDING_LIST_ROUTE
import com.example.tripapp.ui.feature.spending.list.spendingListRoute
import com.example.tripapp.ui.feature.spending.setting.SPENDING_SET_ROUTE
import com.example.tripapp.ui.feature.spending.setting.spendingSetRoute
import com.example.tripapp.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            tripApp()
        }
    }
}


@Composable
fun content(innerPadding: PaddingValues) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) { }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun tripApp(
    navController: NavHostController = rememberNavController()
) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "旅帳清單",
                    fontSize = 18.sp,
                    )},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = purple200,
                    titleContentColor = white200
                ),
                navigationIcon = {
                    Image(
                        painter = painterResource(R.drawable.ic_back),
                        contentDescription = "back",
                        modifier = Modifier.padding(24.dp,0.dp,0.dp,0.dp)
                    )
                },
                actions = {
                    Image(
                        painter = painterResource(R.drawable.ic_delete),
                        contentDescription = "delete",
                        modifier = Modifier.padding(0.dp,0.dp,24.dp,0.dp)
                    )
                }
            )



        },
        bottomBar = {
            BottomAppBar(
                contentColor = white200,
            ) {



            }

        }



    ) { innerPadding ->


        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            TripNavHost(navController)
        }
    }
}


@Composable
fun TripNavHost(navController: NavHostController) {
    NavHost(
        modifier = Modifier,
        navController = navController,
        // 初始頁面
        startDestination = SPENDING_LIST_ROUTE
    ) {
        // 畫面路徑-ruby
        spendingListRoute(navController = navController)
        spendingSetRoute(navController = navController)
        spendingAddRoute(navController = navController)
        spendingDepositRoute(navController = navController)

    }
}



@Preview(showBackground = true)
@Composable
fun tripAppPre(){
    tripApp()
}