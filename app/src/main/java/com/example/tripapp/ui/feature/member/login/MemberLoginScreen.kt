package com.example.tripapp.ui.feature.member.login

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.tripapp.ui.feature.member.turfav.TUR_FAV_ROUTE

@Composable
fun MemberLoginRoute(navController: NavHostController) {
    MemberLoginScreen(
        onTurFavClick = { navController.navigate(TUR_FAV_ROUTE) }
    )
}

@Composable
fun MemberLoginScreen(onTurFavClick: () -> Unit) {

}
