package com.example.tripapp.ui.feature.member

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.tripapp.ui.feature.spending.list.SpendingRoute

val SPENDING_MEMBER_ROUTE = "Member"

fun genMemberNavigationRoute() = SPENDING_MEMBER_ROUTE

fun NavGraphBuilder.memberRoute(navController: NavHostController) {
    composable(
        route = SPENDING_MEMBER_ROUTE,
    ) {
        MemberRoute()
    }
}