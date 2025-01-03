package com.example.tripapp.ui.feature.trip.notes.note

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.tripview.select.SelectScreenRoute

val NOTES_ROUTE = "notes"

fun genShowSchNavigation () = NOTES_ROUTE

fun NavGraphBuilder.notesRoute(navController: NavController) {
    composable(
        route = NOTES_ROUTE
    ) {
        NotesScreenRoute(navController = navController)
    }
}