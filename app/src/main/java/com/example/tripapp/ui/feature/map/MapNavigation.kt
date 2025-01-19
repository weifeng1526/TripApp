package com.example.tripapp.ui.feature.map

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType.Companion.IntType
import androidx.navigation.NavType.Companion.StringType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

val MAP_ROUTE = "Map"
val MAP_WITHOUT_DATE_ROUTE = "MapWithoutDate"

fun  genMapWithoutDateRoute(): String {
    return MAP_WITHOUT_DATE_ROUTE
}

fun genMapNavigationRoute(schNo: Int, dstDate: String): String {
    Log.d("genMapNavigationRoute", "schNo: $schNo")
    Log.d("genMapNavigationRoute", "dstDate: $dstDate")
    return "$MAP_ROUTE/$schNo/$dstDate"
}

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
        val schNo = it.arguments?.getInt("sch_no", 0)
        val dstDate = it.arguments?.getString("dst_date", "")
        Log.d("mapRoute", "schNo: $schNo")
        Log.d("mapRoute", "dstDate: $dstDate")
        MapRoute(
            navController,
            planNumber = it.arguments?.getInt("sch_no", 0) ?: 0,
            planDate = it.arguments?.getString("dst_date", "") ?: ""
        )
    }
    composable(
        route = MAP_WITHOUT_DATE_ROUTE,
    ) {
        MapRoute(navController)
    }
}

