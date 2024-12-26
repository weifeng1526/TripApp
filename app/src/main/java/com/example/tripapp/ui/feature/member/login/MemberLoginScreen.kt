package com.example.tripapp.ui.feature.member.login

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.tripapp.ui.feature.member.turfav.MEMBER_TURFAV_ROUTE

@Composable
fun MemberLoginRoute(navController: NavHostController) {
    MemberLoginScreen(
        onTurFavClick = { navController.navigate(MEMBER_TURFAV_ROUTE) }
    )
}

@Composable
fun MemberLoginScreen(onTurFavClick: () -> Unit) {

}
