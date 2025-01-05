package com.example.tripapp.ui.feature.trip.plan.alter

import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.swithscreen.PlanCreateScreen
import com.example.tripapp.ui.feature.trip.plan.home.PlanHomeViewModel
import com.example.tripapp.ui.feature.trip.restful.RequestVM

val PLAN_ALTER_ROUTE = "plan_alter"
fun genPlanAlterNavigationRoute() = PLAN_ALTER_ROUTE

fun NavGraphBuilder.planAlterRoute(navController: NavHostController) {
    composable(
        route = "${PLAN_ALTER_ROUTE}/{sch_no}",

    ) {

        PlanAlterScreen(
            navController = navController,
            planHomeViewModel = viewModel(),
            requestVM = viewModel(),
            schNo = it.arguments?.getString("sch_no")?.toIntOrNull() ?: 0
        )
    }
}