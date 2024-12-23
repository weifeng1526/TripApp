package com.example.tripapp.ui.feature.map


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun map(){
    var search by remember { mutableStateOf("") }
    var infoListIndex by remember { mutableIntStateOf(2) }
    Box(modifier = Modifier.fillMaxSize()){

        Column (modifier = Modifier.fillMaxWidth().align(Alignment.TopCenter).padding(16.dp)){
            OutlinedTextField(
                value = search,
                onValueChange = {search = it},
                label = { Text(text = "Search")},
                modifier = Modifier.fillMaxWidth()

            )
        }
        Column (modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter)){
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ){
                item {  Row (modifier = Modifier.fillMaxWidth().padding(16.dp)){


                } }
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun mapPreview(){
    map()
}