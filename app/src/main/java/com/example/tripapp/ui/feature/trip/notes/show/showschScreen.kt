package com.example.tripview.show

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tripapp.R
import com.example.tripapp.ui.theme.purple300


@Composable
fun Showsch() {
    Column(
        verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
                .width(412.dp)
                .height(43.dp)
                .background(color = colorResource((R.color.white_300)))
                .horizontalScroll(rememberScrollState())
        ) {
            Row (
                modifier = Modifier
                    .width(86.dp).height(43.dp)
            )
            {
                Column(
                    modifier = Modifier
                        .width(46.dp)
                        .height(43.dp)
                ) {
                    Text(
                        text = "12/16 週一",
                    )
                }
                Image(
                    painter = painterResource(R.drawable.sun),
                    contentDescription = "image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(25.dp).offset(y=8.dp)
                )
            }
            Row (
                modifier = Modifier
                    .width(86.dp).height(43.dp)
            )
            {
                Column(
                    modifier = Modifier
                        .width(46.dp)
                        .height(43.dp)
                ) {
                    Text(
                        text = "12/17 週二",
                    )
                }
                Image(
                    painter = painterResource(R.drawable.rain),
                    contentDescription = "image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(25.dp).offset(y=8.dp)
                )
            }
            Row (
                modifier = Modifier
                    .width(86.dp).height(43.dp)
            )
            {
                Column(
                    modifier = Modifier
                        .width(46.dp)
                        .height(43.dp)
                ) {
                    Text(
                        text = "12/18 週三",
                    )
                }
                Image(
                    painter = painterResource(R.drawable.cloud),
                    contentDescription = "image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(25.dp).offset(y=8.dp)                )
            }
            Row (
                modifier = Modifier
                    .width(86.dp).height(43.dp)
            )
            {
                Column(
                    modifier = Modifier
                        .width(46.dp)
                        .height(43.dp)
                ) {
                    Text(
                        text = "12/19 週四",
                    )
                }
                Image(
                    painter = painterResource(R.drawable.sun),
                    contentDescription = "image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(25.dp).offset(y=8.dp)                )
            }
            Row (
                modifier = Modifier
                    .width(86.dp).height(43.dp)
            )
            {
                Column(
                    modifier = Modifier
                        .width(46.dp)
                        .height(43.dp)
                ) {
                    Text(
                        text = "12/20 週五",
                    )
                }
                Image(
                    painter = painterResource(R.drawable.cloud),
                    contentDescription = "image",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(25.dp).offset(y=8.dp)                )
            }
        }
Column (
    modifier = Modifier.fillMaxSize(1f)){
    Column (modifier = Modifier.height(250.dp).fillMaxWidth(1f)){
//        Image(
//            painter = painterResource(R.drawable.record_point),
//            contentDescription = "image",
//            contentScale = ContentScale.Fit,
//            modifier = Modifier.size(22.dp).offset(x = 10.dp, y = 10.dp)
//        )
        Column (){

        }
        Canvas(modifier = Modifier.size(22.dp).offset(10.dp,10.dp)) {
            drawCircle(color = purple300, radius =  24.0f)
        }
        Canvas(modifier = Modifier.fillMaxHeight()) {
            drawLine(
                color = Color.Gray,
                start = Offset(x = 59f , y = 25f),
                end = Offset(x = 59f , y = size.height),
                strokeWidth = 10f
            )
        }
    }
}
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
        Showsch()
}