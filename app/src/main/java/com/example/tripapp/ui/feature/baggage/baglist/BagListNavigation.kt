package com.example.tripapp.ui.feature.baggage.baglist

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.*


val BAG_LIST_ROUTE = "bag list"

fun genBagListNavigationRoute() = BAG_LIST_ROUTE

fun NavGraphBuilder.BagListRoute(navHostController: NavHostController) {
    composable(
        route = BAG_LIST_ROUTE,
    ) {
        BagRoute(navHostController)
    }
}


