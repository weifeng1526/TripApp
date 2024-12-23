package com.example.tripapp.ui.feature.trip.plan.alter

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

val PLAN_Alter_ROUTE = "plan_alter"
fun genPlanAlterNavigationRoute() = PLAN_Alter_ROUTE

fun NavGraphBuilder.planAlterRoute(navController: NavHostController) {
    composable(
        route = "${PLAN_Alter_ROUTE}",
    ) {
        PlanAlterScreen(
            navController = navController
        )
    }
}