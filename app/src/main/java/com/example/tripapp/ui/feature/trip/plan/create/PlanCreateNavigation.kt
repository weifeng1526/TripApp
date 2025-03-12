package com.example.tripapp.ui.feature.trip.plan.create

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.swithscreen.PlanCreateScreen

private val PLAN_CREATE_ROUTE = "plan_create"

fun genPlanCreateNavigationRoute() = PLAN_CREATE_ROUTE

fun NavGraphBuilder.planCreateRoute(
    navController: NavHostController,
    planCreateViewModel: PlanCreateViewModel
) {
    composable(
        route = genPlanCreateNavigationRoute(),
    ) {
        PlanCreateScreen(
            navController = navController,
            planCreateViewModel = planCreateViewModel,
            navToPlanEditScreen = {
                navController.navigate(it)
                planCreateViewModel.setIsCanNavigate(false)
            }
        )
    }
}