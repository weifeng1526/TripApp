package com.example.tripapp.ui.feature.trip.plan.home

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.swithscreen.PlanHomeScreen

private val PLAN_HOME_ROUTE = "plan_home"

fun genPlanHomeNavigationRoute() = PLAN_HOME_ROUTE

fun NavGraphBuilder.planHomeRoute(
    navController: NavHostController,
    planHomeViewModel: PlanHomeViewModel
) {
    composable(
        route = genPlanHomeNavigationRoute(),
    ) {
        PlanHomeScreen(
            navController = navController,
            planHomeViewModel = planHomeViewModel
        )
    }
}