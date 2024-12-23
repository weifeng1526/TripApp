package com.example.tripapp.ui.feature.member.home.tabs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.tripapp.ui.feature.member.home.MemberRoute

val HOME_TF_ROUTE = "TF"

fun genHomeTFNavigationRoute() = HOME_TF_ROUTE

fun NavGraphBuilder.HomeTFRoute(navController: NavHostController) {
    composable(
        route = HOME_TF_ROUTE,
    ) {
        HomeTFRoute()
    }
}

val HOME_BAG_ROUTE = "Bag"

fun genHomeBagNavigationRoute() = HOME_BAG_ROUTE

fun NavGraphBuilder.HomeBagRoute(navController: NavHostController) {
    composable(
        route = HOME_BAG_ROUTE,
    ) {
        HomeBagRoute()
    }
}
