package com.example.tripview.select

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.tripapp.R
import com.example.tripapp.ui.feature.baggage.baglist.BAG_NAVIGATION_ROUTE
import com.example.tripapp.ui.feature.spending.addlist.SPENDING_ADD_ROUTE
import com.example.tripapp.ui.feature.spending.list.SpendingListScreen
import com.example.tripapp.ui.feature.trip.plan.home.PlanHomeViewModel
import com.example.tripapp.ui.theme.purple300

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SelectSchScreen(navController = rememberNavController())
        }
    }
}

@Composable
fun SelectScreenRoute(navController: NavController){
    SelectSchScreen(navController = navController)
}

@Composable
fun SelectSchScreen(
    navController: NavController,
    planHomeViewModel: PlanHomeViewModel = viewModel(),
    floatingButtonClick: () -> Unit = {}
) {
    val select by planHomeViewModel.plansState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row  (
            modifier = Modifier
                .fillMaxWidth()
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
    .height(205.dp)
    .background(color = colorResource(R.color.white_200))
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
            modifier = Modifier
                .fillMaxWidth()
                .height(78.dp)
        ){
            Column(
                verticalArrangement = Arrangement.spacedBy(0.dp, Alignment.Top),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = "",
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
            FloatingActionButton (onClick = { navController.navigate(BAG_NAVIGATION_ROUTE) }
                , shape = RoundedCornerShape(64.dp), modifier = Modifier
                    .size(50.dp)
                    .offset(-10.dp), containerColor = purple300)
            {
                Image(
                    painter = painterResource(id = R.drawable.myicon_suitcase_1),
                    contentDescription = "image description",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(30.dp)
                )
            }
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
            modifier = Modifier
                .fillMaxWidth()
                .height(78.dp)
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
            Box {
FloatingActionButton (onClick = { navController.navigate(BAG_NAVIGATION_ROUTE) }
    , shape = RoundedCornerShape(64.dp), modifier = Modifier
        .size(50.dp)
        .offset(-10.dp), containerColor = purple300)
    {
    Image(
        painter = painterResource(id = R.drawable.myicon_suitcase_1),
        contentDescription = "image description",
        contentScale = ContentScale.Fit,
        modifier = Modifier.size(30.dp)
    )
}
            }
        }
    }
    }
}



@Preview(showBackground = true)
@Composable
fun SelectPreview() {

        SelectSchScreen(navController = rememberNavController())

}