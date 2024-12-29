package com.example.tripapp.ui.feature.trip.notes.show

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.tripview.show.ShowSchRoute

val SHOW_SCH_ROUTE = "show_sch"

fun genShowSchNavigation () = SHOW_SCH_ROUTE

fun NavGraphBuilder.showSchRoute(navController: NavController) {
    composable(
        route = SHOW_SCH_ROUTE
    ) {
        ShowSchRoute(navController = navController)
    }
}