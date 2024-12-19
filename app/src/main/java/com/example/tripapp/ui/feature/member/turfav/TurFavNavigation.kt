package com.example.tripapp.ui.feature.member.turfav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

val MEMBER_TURFAV_ROUTE = "TurFav"

fun genTurFavNavigationRoute() = MEMBER_TURFAV_ROUTE

fun NavGraphBuilder.turFavRoute(navController: NavHostController) {
    composable(
        route = MEMBER_TURFAV_ROUTE,
    ) {
        TurFavRoute()
    }
}