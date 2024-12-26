package com.example.tripapp.ui.feature.trip.plan.edit

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.tripapp.ui.feature.trip.plan.PlanEditScreen
import com.example.tripapp.ui.feature.trip.plan.home.PlanHomeViewModel

val PLAN_EDIT_ROUTE = "plan_edit"

fun genPlanEditNavigationRoute() = PLAN_EDIT_ROUTE

fun NavGraphBuilder.planEditRoute(navController: NavHostController) {
    composable(
        route = "${PLAN_EDIT_ROUTE}/{schNo}",
    ) {BackStackEntry ->
        PlanEditScreen(
            navController,
            planHomeViewModel = PlanHomeViewModel(),
            schNo = BackStackEntry.arguments?.getString("schNo").let { it?.toInt() ?: 0 })
    }
}