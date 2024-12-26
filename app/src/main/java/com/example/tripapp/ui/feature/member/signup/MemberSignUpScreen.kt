package com.example.tripapp.ui.feature.member.signup

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.tripapp.ui.feature.member.turfav.TUR_FAV_ROUTE

@Composable
fun MemberSignUpRoute(navController: NavHostController) {
    MemberSignUpScreen(
        onTurFavClick = { navController.navigate(TUR_FAV_ROUTE) }
    )
}

@Composable
fun MemberSignUpScreen(onTurFavClick: () -> Unit) {

}
