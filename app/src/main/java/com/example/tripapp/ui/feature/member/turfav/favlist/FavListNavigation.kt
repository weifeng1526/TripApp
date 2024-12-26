package com.example.tripapp.ui.feature.member.turfav.favlist

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

val FAV_LIST_ROUTE = "FavList"

fun genFavListNavigationRoute() = FAV_LIST_ROUTE

fun NavGraphBuilder.favListRoute(navController: NavHostController) {
    composable(
        route = FAV_LIST_ROUTE,
    ) {
        FavListRoute()
    }
}