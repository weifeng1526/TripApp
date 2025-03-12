package com.example.tripapp.ui.feature.trip.plan.edit

import android.util.Log
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.tripapp.ui.feature.trip.plan.PlanEditScreen
import com.example.tripapp.ui.feature.trip.plan.home.PlanHomeViewModel

val PLAN_EDIT_ROUTE = "plan_edit"
val PARAMETER_PLAN_NO = "{sch_no}"
fun genPlanEditNavigationRoute() = "$PLAN_EDIT_ROUTE/$PARAMETER_PLAN_NO"
fun setPlanEditRoute(planNo: Int): String {
    val planEditRoute = genPlanEditNavigationRoute()
    val planNoString: String = planNo.toString()
    val newRoute = planEditRoute.replace(
        oldValue = PARAMETER_PLAN_NO,
        newValue = planNoString
    )
    return newRoute
}



fun NavGraphBuilder.planEditRoute(navController: NavHostController) {
    composable(
        route = genPlanEditNavigationRoute(),
    ) {BackStackEntry ->
        PlanEditScreen(
            navController = navController,
            planEditViewModel = viewModel(),
            planHomeViewModel = viewModel(),
            requestVM = viewModel(),
            schNo = BackStackEntry.arguments?.getString("sch_no").let { it?.toIntOrNull() ?: 0 }
        )
    }
}