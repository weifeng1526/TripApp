package com.example.tripapp.ui.feature.trip.plan.create

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.swithscreen.PlanCreateScreen
import com.example.tripapp.ui.feature.trip.restfulPlan.Plan

val PLAN_CREATE_ROUTE = "plan_create"

fun genPlanCreateNavigationRoute() = PLAN_CREATE_ROUTE

fun NavGraphBuilder.planCreateRoute(navController: NavHostController) {
    composable(
        route = PLAN_CREATE_ROUTE,
    ) {
        val planCreateViewModel: PlanCreateViewModel = viewModel()
        PlanCreateScreen(
            navController = navController,
            planCreateViewModel = planCreateViewModel,
            requestVM = viewModel()
        )
    }
    composable(
        route = "${PLAN_CREATE_ROUTE}/{isSample}/{sch_name}/{sch_con}/{sch_cur}",
    ) {
        val planCreateViewModel: PlanCreateViewModel = viewModel()
        planCreateViewModel.setIsSample(true)
        var plan = Plan().apply {
            schName = it.arguments?.getString("sch_name") ?: ""
            schCon = it.arguments?.getString("sch_con") ?: ""
            schCur = it.arguments?.getString("sch_cur") ?: ""
        }
        planCreateViewModel.setPlanForCreate(plan)
        PlanCreateScreen(
            navController = navController,
            planCreateViewModel = planCreateViewModel,
            requestVM = viewModel()
        )
    }
}