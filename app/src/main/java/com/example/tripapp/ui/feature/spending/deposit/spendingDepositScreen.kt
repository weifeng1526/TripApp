package com.example.tripapp.ui.feature.spending.deposit

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tripapp.R
import com.example.tripapp.ui.theme.black300
import com.example.tripapp.ui.theme.black600
import com.example.tripapp.ui.theme.black900
import com.example.tripapp.ui.theme.purple100
import com.example.tripapp.ui.theme.purple200
import com.example.tripapp.ui.theme.purple300
import com.example.tripapp.ui.theme.white100
import com.example.tripapp.ui.theme.white300
import com.example.tripapp.ui.theme.white400


@Composable
fun SpendingRoute() {
    spendingDepositRoute()
}

@Preview
@Composable
fun PreviewSpendingRoute() {
    spendingDepositRoute()
}

@Composable
fun spendingDepositRoute() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(white100)

    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(white100, white300)
                    ),
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp, 20.dp,32.dp,0.dp),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "儲值金額 ",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = black900
                    )
                    Button(
                        onClick = {},
                        border = BorderStroke(2.dp, white400),
                        colors = ButtonDefaults.buttonColors(
                            contentColor = purple300, containerColor = Color.Transparent
                        ),
                        modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)

                    ) {
                        Text(
                            text = "日幣",
                            color = purple300,
                        )
                        Image(
                            painter = painterResource(R.drawable.ic_popselect),
                            contentDescription = "pop",
                            modifier = Modifier
                                .size(20.dp, 16.dp)
                                .padding(8.dp, 0.dp, 0.dp, 0.dp)
                        )
                    }

                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 48.dp, 0.dp, 28.dp),
                    horizontalAlignment = Alignment.End,


                    ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "12,000",
                            fontSize = 44.sp,
                            fontWeight = FontWeight.Bold,
                            color = black900
                        )
                        Text(
                            text = "JPY",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(12.dp, 0.dp, 0.dp, 0.dp),
                            color = black900

                        )
                    }
                }
            }
            HorizontalDivider(
                thickness = 2.dp, color = white400
            )


        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp,20.dp)

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Text(text = "2024/12/24  19:25",
                    fontSize = 16.sp)
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text =  "6,000",
                        fontSize = 20.sp,
                        color = black600,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(0.dp,0.dp,12.dp,0.dp))
                    Text(text = "JPY",
                        color = black600,
                        fontWeight = FontWeight.Medium
                        )
                }

            }
        }

        HorizontalDivider(
            thickness = 2.dp,
            color = white400
            )


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp,20.dp)

        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Text(text = "2024/12/24  19:25",
                    fontSize = 16.sp)
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(text =  "6,000",
                        fontSize = 20.sp,
                        color = black600,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(0.dp,0.dp,12.dp,0.dp))
                    Text(text = "JPY",
                        color = black600,
                        fontWeight = FontWeight.Medium
                    )
                }

            }
        }

        HorizontalDivider(
            thickness = 2.dp,
            color = white400
        )

    }

}