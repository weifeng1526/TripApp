package com.example.swithscreen

import androidx.annotation.StringRes
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.tripapp.R

enum class Screen(@StringRes title: Int) {
    ScheduleHome(R.string.scheduleHomeScreen),
    ScheduleEdite(R.string.scheduleEditScreen),
    ScheduleCreate(R.string.scheduleCreateScreen),
    Crew(R.string.crewScreen),
    CrewAdd(R.string.crewAddScreen)
}

@Composable
fun Main(navController: NavHostController = rememberNavController()) {
    NavHost(navController = navController,
        startDestination = Screen.ScheduleHome.name
    ) {
        composable(route = Screen.ScheduleHome.name) {
            ScheduleHomeScreen(navController = navController)
        }
        composable(route = Screen.ScheduleEdite.name) {
            ScheduleEditScreen(navController = navController)
        }
        composable(route = Screen.ScheduleCreate.name) {
            ScheduleCreateScreen(navController = navController)
        }
        composable(route = Screen.Crew.name) {
            CrewScreen(navController = navController)
        }
        composable(route = Screen.CrewAdd.name) {
            CrewAddScreen(navController = navController)
        }
    }
}