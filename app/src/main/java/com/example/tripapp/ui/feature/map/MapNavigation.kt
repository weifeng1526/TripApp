package com.example.tripapp.ui.feature.map

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.tripapp.ui.feature.member.login.MemberLoginRoute

val MAP_ROUTE = "map"

fun genMapNavigationRoute() = MAP_ROUTE

fun NavGraphBuilder.MapRoute(navController: NavHostController) {
    composable(
        route = MAP_ROUTE,
    ) {
        MemberLoginRoute(navController)
    }
}