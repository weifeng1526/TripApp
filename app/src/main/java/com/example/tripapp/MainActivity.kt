package com.example.tripapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.tripapp.ui.feature.spending.list.spendingListRoute
import com.example.tripapp.ui.feature.spending.setting.SPENDING_SET_ROUTE
import com.example.tripapp.ui.feature.spending.setting.spendingSetRoute

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TripApp()
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
fun TripApp(
    navController: NavHostController = rememberNavController()
) {

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "HIHIHIHIHI") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
            }
        }

    ) { innerPadding ->
        content(innerPadding)

        Column(
            modifier = Modifier
                .padding(innerPadding)
        ) {
            TripNavHost()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TripNavHost() {
    val navController = rememberNavController()
    NavHost(
        modifier = Modifier,
        navController = navController,
        // 初始頁面
        startDestination = SPENDING_SET_ROUTE
    ) {
        // 畫面路徑
        spendingListRoute(navController = navController)
        spendingSetRoute(navController = navController)
    }
}