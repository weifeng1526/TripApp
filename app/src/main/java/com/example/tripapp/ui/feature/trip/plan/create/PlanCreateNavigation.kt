package com.example.tripapp.ui.feature.trip.plan.create

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.swithscreen.PlanCreateScreen

val PLAN_CREATE_ROUTE = "plan_create"

fun genPlanCreateNavigationRoute() = PLAN_CREATE_ROUTE

fun NavGraphBuilder.planCreateRoute(navController: NavHostController) {
    composable(
        route = PLAN_CREATE_ROUTE,
    ) {
        PlanCreateScreen(navController)
    }
}