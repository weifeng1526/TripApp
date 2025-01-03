package com.example.tripapp.ui.feature.trip.notes.show

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.tripapp.ui.feature.trip.plan.edit.PlanEditViewModel
import com.example.tripview.show.ShowSchRoute

val SHOW_SCH_ROUTE = "show_sch"

fun genShowSchNavigation () = SHOW_SCH_ROUTE

fun NavGraphBuilder.showSchRoute(navController: NavController) {
    composable(
//        route = "$SHOW_SCH_ROUTE/{sch_no}"
        route = SHOW_SCH_ROUTE
    ) {BackStackEntry ->
        ShowSchRoute(
            navController = navController,
            schNo = 1
//            schNo = BackStackEntry.arguments?.getString("sch_no").let { it?.toIntOrNull() ?: 0 }
        )
    }
}