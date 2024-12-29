package com.example.tripapp.ui.feature.trip.notes.note

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tripapp.R
import com.example.tripapp.ui.feature.trip.plan.edit.Destination
import com.example.tripapp.ui.feature.trip.plan.edit.PlanEditViewModel

@Composable
fun  NotesScreenRoute(navController: NavController){
NotesScreen(navController = navController, destination = Destination())
}

@Composable
fun NotesDate(
    navController: NavController,
    planEditViewModel: PlanEditViewModel
){
val date by planEditViewModel.dstsState.collectAsState()
}

@Composable
fun NotesScreen(
    navController: NavController,
    destination: Destination
){
Column (modifier = Modifier.fillMaxSize(1f)) {
Row (modifier = Modifier.fillMaxWidth(1f)){
    Text(
        text = destination.dstName

    )
    Row (
        modifier = Modifier.fillMaxWidth(1f).padding(10.dp)
    ){
        Image(
            painter = painterResource(R.drawable.aaa),
            contentDescription = null
        )
    }
}
}
}

@Preview
@Composable
fun NotesScreenPreview(){
    NotesScreen(navController = rememberNavController(), destination = Destination())
}