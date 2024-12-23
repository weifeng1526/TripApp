package com.example.tripapp.ui.feature.spending.deposit

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable


val SPENDING_DEPOSIT_ROUTE = "Deposit"

fun getSpendingDepositNavigationRoute() = SPENDING_DEPOSIT_ROUTE

fun NavGraphBuilder.spendingDepositRoute(navController: NavHostController) {
    composable(
        route = SPENDING_DEPOSIT_ROUTE,
    ) {
        SpendingRoute()
    }
}
