package com.example.tripapp.ui.feature.baggage.itemlist

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable


val ADD_ITEM_ROUTE = "add item"

fun genAddItemNavigationRoute() = ADD_ITEM_ROUTE

fun NavGraphBuilder.AddItemRoute(navHostController: NavHostController) {
    composable(
        route = ADD_ITEM_ROUTE,
    ) {
        BagItemRoute(navHostController)
    }
}
