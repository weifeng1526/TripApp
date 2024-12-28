package com.example.tripapp.ui.feature.member.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ButtonColors
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tripapp.R
import com.example.tripapp.ui.theme.black600
import com.example.tripapp.ui.theme.black700
import com.example.tripapp.ui.theme.black800
import com.example.tripapp.ui.theme.purple200
import com.example.tripapp.ui.theme.white300
import com.example.tripapp.ui.theme.white400

@Composable
fun MemberLoginRoute(navController: NavHostController) {
    MemberLoginScreen(
//        onTurFavClick = { navController.navigate(TUR_FAV_ROUTE) }
    )
}

@Preview
@Composable
fun PreviewMemberLoginRoute() {
    MemberLoginScreen()
}

@Composable
fun MemberLoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = white400)
    ) {
        //logo圖案要再改
        Image(
            painter = painterResource(R.drawable.lets_icons__suitcase_light),
            contentDescription = "AppLogo",
            modifier = Modifier
                .padding(top = 16.dp)
                .size(70.dp)
                .graphicsLayer {
                    this.alpha = 0.5f
                }
        )
        Text(
            text = "旅友 TravelMate",
            modifier = Modifier
                .graphicsLayer {
                    this.alpha = 0.5f
                }
        )

        Spacer(modifier = Modifier.padding(28.dp))

        Text(
            text = "登入",
            fontSize = 24.sp,
        )

        Spacer(modifier = Modifier.padding(12.dp))

        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(horizontal = 24.dp)
        ) {
            Text(
                text = "電子信箱",
                fontSize = 12.sp,
            )
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = { Text(text = "example@mail.com") },
                singleLine = true,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "password"
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "clear",
                        modifier = Modifier.clickable {
                            email = ""
                        }
                    )
                },
            )
        }

        Spacer(modifier = Modifier.padding(8.dp))

        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(horizontal = 24.dp)
        ) {
            Text(
                text = "密碼",
                fontSize = 12.sp,
            )
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                placeholder = { Text(text = "請輸入6-8位數英數文字") },
                singleLine = true,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "password"
                    )
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = "clear",
                        modifier = Modifier.clickable {
                            password = ""
                        }
                    )
                },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            )
        }

        Spacer(modifier = Modifier.padding(32.dp))

        Button(
            onClick = { },
            modifier = Modifier
                .padding(),
            colors = ButtonDefaults.buttonColors(
                containerColor = purple200,
                contentColor = white300
            )
        ) {
            Text(
                text = "登入",
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.padding(8.dp))

        HorizontalDivider(
            modifier = Modifier
                .padding(horizontal = 48.dp, vertical = 4.dp),
            color = black600
        )

        Text(
            modifier = Modifier
                .padding(12.dp)
                .clickable {  },
            text = "註冊",
            fontSize = 16.sp
        )
    }
}