package com.example.tripapp.ui.feature.member.turfav

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

val TUR_FAV_ROUTE = "TurFav"

fun genTurFavNavigationRoute() = TUR_FAV_ROUTE

fun NavGraphBuilder.turFavRoute(navController: NavHostController) {
    composable(
        route = TUR_FAV_ROUTE,
    ) {
        TurFavRoute(navController)
    }
}