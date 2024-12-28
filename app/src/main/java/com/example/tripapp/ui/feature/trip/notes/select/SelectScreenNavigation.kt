package com.example.tripapp.ui.feature.trip.notes.select

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.tripview.select.View

val SELECT_ROUTE = "select"

fun genShowSchNavigation () = SELECT_ROUTE

fun NavGraphBuilder.selectRoute(navController: NavController) {
    composable(
        route = SELECT_ROUTE
    ) {
        View(navController = navController)
    }
}