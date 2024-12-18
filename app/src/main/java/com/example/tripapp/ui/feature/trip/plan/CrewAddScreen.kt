package com.example.swithscreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun CrewAddScreen(navController: NavController) {
    Text(text = Screen.CrewAdd.name)
}

@Preview
@Composable
fun PreviewCrewAddScreen() {
    CrewAddScreen(rememberNavController())
}