package com.example.tripapp.ui.feature.map

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType.Companion.IntType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

val MAP_ROUTE = "Map"

fun genMapNavigationRoute(schNo: Int) = "$MAP_ROUTE/$schNo"

fun NavGraphBuilder.mapRoute(navController: NavHostController) {
    composable(
        route = "${MAP_ROUTE}/{sch_no}",
        arguments = listOf(
            navArgument("sch_no") {
                type = IntType
            }
        )
    ) {
        MapRoute(
            navController,
            planNumber = it.arguments?.getString("sch_no").let { it?.toIntOrNull() ?: 0 }
        )
    }
}
