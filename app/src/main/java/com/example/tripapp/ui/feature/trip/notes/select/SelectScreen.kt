package com.example.tripview.select

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tripapp.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

                View()

        }
    }
}

@Composable
fun View() {
    Column(
        verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
        horizontalAlignment = Alignment.Start
    ) {
        Row  (
            modifier = Modifier.width(412.dp)
                .height(39.dp)
                .background(color = colorResource(R.color.white_200))
        ){ Text(
            text = "即將出發",
            modifier = Modifier.padding(
                 top = 10.dp, end = 10.dp, bottom = 10.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF000000)
        ) }

Row (modifier = Modifier
    .width(383.dp)
    .height(205.dp).background(color = colorResource(R.color.white_200))
    ){ 
    Image(
        painter = painterResource(R.drawable.aaa),
        contentDescription = "image",
        contentScale = ContentScale.FillBounds
    )
}
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(383.dp).height(78.dp).background(color = colorResource(R.color.white_200))
        ){
            Column(
                verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = "schName",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000),
                )
                Text(
                    text = "2024/12/16~2024/12/22",
                        fontSize = 16.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000),
                    )
            }
                Image(
                    painter = painterResource(id = R.drawable.myicon_suitcase_1),
                    contentDescription = "image description",
                    contentScale = ContentScale.None,
                    modifier = Modifier.padding(end = 10.dp).size(32.dp)
                        .background(
                        color = colorResource(R.color.purple_300),
                        shape = RoundedCornerShape(50)
                    ).align(Alignment.CenterVertically)
                )
        }
        Column (
            modifier = Modifier
                .width(200.dp)
                .height(30.dp)
        ){ Text(
            text = "所有行程",
            modifier = Modifier.padding(
                 end = 10.dp, bottom = 10.dp),
            fontSize = 16.sp,
            fontWeight = FontWeight(400),
            color = Color(0xFF000000)
        ) }
Column (modifier = Modifier
    .width(410.dp)
    .height(447.dp)){
    Row (
        modifier = Modifier
            .width(383.dp)
            .height(205.dp)
    ){
        Image(
            painter = painterResource(R.drawable.aaa),
            contentDescription = "image",
            contentScale = ContentScale.FillBounds)
    }
        Row (
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(412.dp).height(78.dp)
        ){
            Column(
                verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = "出去走走",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                )
                Text(
                    text = "2024/12/16~2024/12/22",
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                )
            }
            Image(
                painter = painterResource(id = R.drawable.myicon_suitcase_1),
                contentDescription = "image description",
                contentScale = ContentScale.None,
                modifier = Modifier.padding(end = 10.dp).size(32.dp)
                    .background(
                        color = colorResource(R.color.purple_300),
                        shape = RoundedCornerShape(50)
                    ).align(Alignment.CenterVertically)
            )
        }
    } }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

        View()

}