package com.example.tripapp.ui.feature.map


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tripapp.R






@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun map() {
    var search by remember { mutableStateOf("") }
    var infoListIndex by remember { mutableIntStateOf(2) }
    var type= "熟食店"
    var name= "朴子當歸鴨"
    var address= "100台北市中正區中華路二段313巷27號一樓"
    var button= "餐廳"
    var listName= "清單1"
    var checkList by remember { mutableStateOf(false) }
    var checkState = rememberModalBottomSheetState(skipPartiallyExpanded = false)
//    收藏清單列表
    var favorList by remember { mutableStateOf(false) }
    var favorState2 = rememberModalBottomSheetState(skipPartiallyExpanded = false)
//    收藏景點列表
    var unlike =Icons.Default.FavoriteBorder
    var like =Icons.Default.Favorite
    Box(modifier = Modifier.fillMaxSize()) {

        Column(modifier = Modifier.fillMaxWidth().align(Alignment.TopCenter).padding(16.dp)) {
            OutlinedTextField(
                value = search,
                onValueChange = { search = it },
                label = { Text(text = "Search") },
                modifier = Modifier.fillMaxWidth()

            )
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                item {
                    Button(onClick = {}) {
                        Text(text = button)
                    }
                }
                items(8) {
                    Button(onClick = {}) {
                        Text(text = button)
                    }
                }
            }
        }

        Column(modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter)) {


            ElevatedButton(onClick = { checkList = true }) {
                Text("Elevated")
            }
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(8) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        //                        記得換圖
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = stringResource(R.string.app_name),
                            modifier = Modifier.size(80.dp).padding(8.dp),
                            contentScale = ContentScale.FillHeight
                        )
                        Column(modifier = Modifier.padding(start = 8.dp)) {
                            Text(type, maxLines = 1, fontSize = 16.sp)
                            Spacer(modifier = Modifier.padding(top = 8.dp))
                            Text(name, maxLines = 1, fontSize = 20.sp)
                            Spacer(modifier = Modifier.padding(top = 8.dp))
                            Text(
                                address,
                                maxLines = 2,
                                fontSize = 12.sp,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        Icon(imageVector = Icons.Default.Add,
                            contentDescription = "add",
                            tint = Color.Black,
                            modifier = Modifier.size(40.dp).clickable {})
                        Icon(
                            imageVector = unlike,
                            contentDescription = "like",
                            tint = Color.Blue,
                            modifier = Modifier.size(40.dp).clickable {})

                    }
                }


            }
        }
        if (checkList) {
            ModalBottomSheet(
                modifier = Modifier.fillMaxHeight(),
                sheetState = checkState,
                onDismissRequest = { checkList = false }
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical =8
                        .dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(5) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu",
                                tint = Color.Blue,
                                modifier = Modifier.padding(4.dp).clickable { favorList=true }
                            )
                        Text(text = listName, fontSize = 20.sp)


                    } }
                }
            }
        }
        if (favorList) {
            ModalBottomSheet(
                modifier = Modifier.fillMaxHeight(),
                sheetState = favorState2,
                onDismissRequest = { favorList = false }
            ) {
                checkList=false
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(10) { Row(modifier = Modifier.fillMaxWidth()) {
//                        記得換圖
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_background),
                            contentDescription = stringResource(R.string.app_name),
                            modifier = Modifier.size(80.dp).padding(8.dp),
                            contentScale = ContentScale.FillHeight
                        )
                        Column(modifier = Modifier.padding(start = 0.dp)) {
                            Text(type, maxLines = 1, fontSize = 16.sp)
                            Spacer(modifier = Modifier.padding(top = 8.dp))
                            Text(name, maxLines = 1, fontSize = 20.sp)
                            Spacer(modifier = Modifier.padding(top = 8.dp))
                            Text(
                                address,
                                maxLines = 2,
                                fontSize = 12.sp,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                        Icon(imageVector = Icons.Default.Add,
                            contentDescription = "add",
                            tint = Color.Black,
                            modifier = Modifier.size(40.dp).clickable {})
                        Icon(
                            imageVector = unlike,
                            contentDescription = "like",
                            tint = Color.Blue,
                            modifier = Modifier.size(40.dp).clickable {})

                    } }


                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun mapPreview(){
    map()
}