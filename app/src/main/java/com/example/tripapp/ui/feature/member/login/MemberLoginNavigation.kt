package com.example.tripapp.ui.feature.member.login

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

val MEMBER_LOGIN_KEY_ID = "Login"
val MEMBER_LOGIN_ROUTE = "Login?account = {account}&password={password}"

fun genMemberLoginNavigationRoute() = MEMBER_LOGIN_ROUTE

fun genMemberLoginNavigationKey(account: String, password: String) =
    "$MEMBER_LOGIN_KEY_ID?account=$account&password=$password"

fun NavGraphBuilder.memberLoginRoute(navController: NavHostController) {
    composable(
        route = MEMBER_LOGIN_ROUTE,
    ) {
        MemberLoginRoute(
            navController = navController,
        )
    }
}

val MEMBER_LOGIN_EMPTY_ROUTE = "Login"

fun NavGraphBuilder.memberLoginEmptyRoute(navController: NavHostController) {
    composable(
        route = MEMBER_LOGIN_EMPTY_ROUTE,
    ) {
        MemberLoginRoute(
            navController = navController,
        )
    }
}