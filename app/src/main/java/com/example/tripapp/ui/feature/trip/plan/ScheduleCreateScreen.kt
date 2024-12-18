package com.example.swithscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tripapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduleCreateScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(6.dp),
                title = {
                    Text(
                        text = "建立行程",
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navController.navigate(Screen.ScheduleHome.name) },
                        modifier = Modifier
                            .size(52.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "Back Icon",
                            modifier = Modifier.size(50.dp),
                            tint = Color.Unspecified
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { navController.navigate(Screen.ScheduleEdite.name) },
                        modifier = Modifier
                            .size(52.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.confirm),
                            contentDescription = "Confirm Icon",
                            modifier = Modifier.size(50.dp),
                            tint = Color.Unspecified
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.LightGray
                )
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .padding(6.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .padding(bottom = 20.dp)
                        .background(Color.LightGray),
                ) {
                    Box(
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Image(
                            modifier = Modifier
                                .padding(6.dp)
                                .fillMaxSize()
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { },
                            painter = painterResource(id = R.drawable.dstt),
                            contentDescription = "dstt description",
                            contentScale = ContentScale.FillBounds
                        )
                        IconButton(
                            onClick = { },
                            modifier = Modifier.size(52.dp)
                                .align(Alignment.BottomEnd)
                                .padding(10.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.picture),
                                contentDescription = "Add Icon",
                                modifier = Modifier.size(50.dp),
                                tint = Color.Unspecified
                            )
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .background(Color.LightGray)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(6.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(
                                6.dp,
                                Alignment.CenterVertically
                            ),
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {
                            Text(
                                text = "行程名稱",
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    color = Color(0xFF000000)
                                ),
                                modifier = Modifier.fillMaxWidth()
                            )
                            TextField(
                                value = "",
                                onValueChange = {},
                                modifier = Modifier.fillMaxWidth(),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(
                                    20.dp,
                                    Alignment.Start
                                )
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxWidth(0.7f),
                                    verticalArrangement = Arrangement.spacedBy(
                                        6.dp,
                                        Alignment.CenterVertically
                                    )
                                ) {
                                    Text(
                                        text = "前往國家",
                                        style = TextStyle(
                                            fontSize = 24.sp
                                        ),
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    TextField(
                                        value = "",
                                        onValueChange = {},
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalArrangement = Arrangement.spacedBy(
                                        6.dp,
                                        Alignment.CenterVertically
                                    ),
                                ) {
                                    Text(
                                        text = "幣別",
                                        style = TextStyle(
                                            fontSize = 24.sp
                                        ),
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    TextField(
                                        value = "",
                                        onValueChange = {},
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalArrangement = Arrangement.spacedBy(
                                        6.dp,
                                        Alignment.CenterVertically
                                    ),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    Text(
                                        text = "出發日期",
                                        style = TextStyle(
                                            fontSize = 24.sp
                                        ),
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    TextField(
                                        value = "",
                                        onValueChange = {},
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalArrangement = Arrangement.spacedBy(
                                        6.dp,
                                        Alignment.CenterVertically
                                    ),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    Text(
                                        text = "結束日期",
                                        style = TextStyle(
                                            fontSize = 24.sp
                                        ),
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    TextField(
                                        value = "",
                                        onValueChange = {},
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

@Preview
@Composable
fun PreviewScheduleCreateScreen() {
    ScheduleCreateScreen(rememberNavController())
}