package com.example.tripapp.ui.feature.spending.list

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable


val SPENDING_LIST_ROUTE = "Spending"

fun getSpendingListNavigationRoute() = SPENDING_LIST_ROUTE

fun NavGraphBuilder.spendingListRoute(navController: NavHostController) {
    composable(
        route = SPENDING_LIST_ROUTE,
    ) {
        SpendingRoute()
    }
}
