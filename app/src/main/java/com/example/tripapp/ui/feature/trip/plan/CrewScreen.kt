package com.example.swithscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun CrewScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Button(
            onClick = {
                navController.navigate(Screen.CrewAdd.name)
            }
        ) {
            Text(text = "新增朋友")
        }
    }
}

@Preview
@Composable
fun PreviewCrewScreen() {
    CrewScreen(rememberNavController())
}