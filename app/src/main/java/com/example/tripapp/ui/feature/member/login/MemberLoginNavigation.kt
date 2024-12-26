package com.example.tripapp.ui.feature.member.login

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.tripapp.ui.feature.member.home.MemberRoute

val MEMBER_LOGIN_ROUTE = "LOGIN"

fun genMemberNavigationRoute() = MEMBER_LOGIN_ROUTE

fun NavGraphBuilder.memberloginRoute(navController: NavHostController) {
    composable(
        route = MEMBER_LOGIN_ROUTE,
    ) {
        MemberLoginRoute(navController)
    }
}