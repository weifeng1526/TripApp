package com.example.tripapp.ui.feature.spending.addlist

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.tripapp.ui.feature.spending.list.SpendingListRoute

// 頁面的種類
val SPENDING_ADD_KEYID = "Add"

// 頁面的參數的key
val SPENDING_ADD_ARGS_SCHNO = "schno"

// 頁面的真正的路徑
val SPENDING_ADD_ROUTE = "$SPENDING_ADD_KEYID/{$SPENDING_ADD_ARGS_SCHNO}"

fun getSpendingAddNavigationRoute(schno: Int) = "$SPENDING_ADD_KEYID/$schno"

fun NavGraphBuilder.spendingAddRoute(navController: NavHostController) {
    composable(
        route = SPENDING_ADD_ROUTE,
        arguments = listOf(
            navArgument(SPENDING_ADD_ARGS_SCHNO) { type = NavType.IntType }
        )
    ) {
        val schno = it.arguments?.getInt(SPENDING_ADD_ARGS_SCHNO, 0) ?: 0
        SpendingAddRoute(navHostController = navController, schNo = schno)

    }
}
