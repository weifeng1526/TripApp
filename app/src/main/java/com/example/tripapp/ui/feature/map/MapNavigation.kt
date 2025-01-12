package com.example.tripapp.ui.feature.map

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType.Companion.IntType
import androidx.navigation.NavType.Companion.StringType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

val MAP_ROUTE = "Map"

fun genMapNavigationRoute(schNo: Int,dstDate: String) = "$MAP_ROUTE/$schNo/$dstDate"

fun NavGraphBuilder.mapRoute(navController: NavHostController) {
    composable(
        route = "${MAP_ROUTE}/{sch_no}/{dst_date}",
        arguments = listOf(
            navArgument("sch_no") {
                type = IntType
            }, navArgument("dst_date") {
                type = StringType
            }
        )
    ) {
        MapRoute(
            navController,
            planNumber = it.arguments?.getString("sch_no").let { it?.toIntOrNull() ?: 0 },
            planDate = it.arguments?.getString("dst_date") ?: ""
        )
    }
}
