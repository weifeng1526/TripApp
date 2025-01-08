package com.example.tripapp.ui.feature.baggage.baglist

import androidx.fragment.app.FragmentManager.BackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.*

/**
 * todo 2-1 將首頁的路由獨立出來
 * */

val BAG_NAVIGATION_ROUTE = "bag"

fun genBagNavigationRoute() = BAG_NAVIGATION_ROUTE


//fun NavGraphBuilder.bagListScreenRoute(navController: NavHostController) {
//    composable(
//        route = BAG_NAVIGATION_ROUTE,
//    ) {
//        BagRoute(navController = navController)
//    }
//}


fun NavGraphBuilder.bagListScreenRoute(navController: NavHostController) {
    composable(
        route = "$BAG_NAVIGATION_ROUTE/{sch_no}",
    ) {BackStackEntry ->
        BagRoute(navController = navController,
            schNo = BackStackEntry.arguments?.getString("sch_no").let { it?.toIntOrNull() ?: 0 })
    }
}


