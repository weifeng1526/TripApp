package com.example.tripapp.ui.feature.spending.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tripapp.R
import com.example.tripapp.ui.theme.*

@Composable
fun tripA() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(white100)
    ) {
        Column(
            modifier = Modifier
                .background(white100)
                .padding(20.dp, 12.dp,20.dp, 8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "消費明細",
                fontSize = 15.sp
            )
        }



        Column(
            modifier = Modifier
                .padding(20.dp, 0.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_member_01),
                    contentDescription = "member icon",
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp)
                )
                Column(

                ) {
                    Text(
                        text = "Rubyyyyyer",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Row(
                        modifier = Modifier.padding(0.dp, 4.dp),
                    ) {
                        Text(
                            text = "交通",
                            color = black600,
                        )
                        VerticalDivider(
                            thickness = 2.dp,
                            color = black500,
                            modifier = Modifier
                                .padding(8.dp, 4.dp)
                                .height(14.dp)
                        )
                        Text(
                            text = "高鐵車票",
                            color = black600
                        )
                    }


                    Text(
                        text = "2025/12/25 19:25",
                        color = black600
                    )

                }
                Column() {
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {


                        Column(
                            horizontalAlignment = Alignment.End,


                        ) {
                            Text(
                                text = "5,000",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.ExtraBold,
                                textAlign = TextAlign.End,
                                modifier = Modifier.padding(0.dp,0.dp,0.dp,4.dp)

                            )
                            Text(
                                text = "=1,000",
                                fontSize = 15.sp,
                                color = black600,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.End

                                )


                        }


                        Text(
                            text = "JPY",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 24.sp,
                            modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)
                        )


                    }
                    Text(
                        text = "Share People more +",
                        fontSize = 14.sp,
                        lineHeight = 24.sp,

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp,4.dp,0.dp,0.dp),
                        textAlign = TextAlign.End,
                    )
                }
            }
        }
        HorizontalDivider(
            thickness = 1.dp,
            color = white400,
            modifier = Modifier.padding(0.dp,12.dp)
            )


//copy------------------------------------------------------------------------
        Column(
            modifier = Modifier
                .padding(20.dp, 0.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_member_01),
                    contentDescription = "member icon",
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp)
                )
                Column(

                ) {
                    Text(
                        text = "Rubyyyyyer",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Row(
                        modifier = Modifier.padding(0.dp, 4.dp),
                    ) {
                        Text(
                            text = "交通",
                            color = black600,
                        )
                        VerticalDivider(
                            thickness = 2.dp,
                            color = black500,
                            modifier = Modifier
                                .padding(8.dp, 4.dp)
                                .height(14.dp)
                        )
                        Text(
                            text = "高鐵車票",
                            color = black600
                        )
                    }


                    Text(
                        text = "2025/12/25 19:25",
                        color = black600
                    )

                }
                Column() {
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {


                        Column(
                            horizontalAlignment = Alignment.End,


                            ) {
                            Text(
                                text = "5,000",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.ExtraBold,
                                textAlign = TextAlign.End,
                                modifier = Modifier.padding(0.dp,0.dp,0.dp,4.dp)

                            )
                            Text(
                                text = "=1,000",
                                fontSize = 15.sp,
                                color = black600,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.End

                            )


                        }


                        Text(
                            text = "JPY",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 24.sp,
                            modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)
                        )


                    }
                    Text(
                        text = "Share People more +",
                        fontSize = 14.sp,
                        lineHeight = 24.sp,

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp,4.dp,0.dp,0.dp),
                        textAlign = TextAlign.End,
                    )
                }
            }
        }
        HorizontalDivider(
            thickness = 1.dp,
            color = white400,
            modifier = Modifier.padding(0.dp,12.dp)
        )
        Column(
            modifier = Modifier
                .padding(20.dp, 0.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_member_01),
                    contentDescription = "member icon",
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp)
                )
                Column(

                ) {
                    Text(
                        text = "Rubyyyyyer",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Row(
                        modifier = Modifier.padding(0.dp, 4.dp),
                    ) {
                        Text(
                            text = "交通",
                            color = black600,
                        )
                        VerticalDivider(
                            thickness = 2.dp,
                            color = black500,
                            modifier = Modifier
                                .padding(8.dp, 4.dp)
                                .height(14.dp)
                        )
                        Text(
                            text = "高鐵車票",
                            color = black600
                        )
                    }


                    Text(
                        text = "2025/12/25 19:25",
                        color = black600
                    )

                }
                Column() {
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {


                        Column(
                            horizontalAlignment = Alignment.End,


                            ) {
                            Text(
                                text = "5,000",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.ExtraBold,
                                textAlign = TextAlign.End,
                                modifier = Modifier.padding(0.dp,0.dp,0.dp,4.dp)

                            )
                            Text(
                                text = "=1,000",
                                fontSize = 15.sp,
                                color = black600,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.End

                            )


                        }


                        Text(
                            text = "JPY",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 24.sp,
                            modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)
                        )


                    }
                    Text(
                        text = "Share People more +",
                        fontSize = 14.sp,
                        lineHeight = 24.sp,

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp,4.dp,0.dp,0.dp),
                        textAlign = TextAlign.End,
                    )
                }
            }
        }
        HorizontalDivider(
            thickness = 1.dp,
            color = white400,
            modifier = Modifier.padding(0.dp,12.dp)
        )
        Column(
            modifier = Modifier
                .padding(20.dp, 0.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_member_01),
                    contentDescription = "member icon",
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp)
                )
                Column(

                ) {
                    Text(
                        text = "Rubyyyyyer",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Row(
                        modifier = Modifier.padding(0.dp, 4.dp),
                    ) {
                        Text(
                            text = "交通",
                            color = black600,
                        )
                        VerticalDivider(
                            thickness = 2.dp,
                            color = black500,
                            modifier = Modifier
                                .padding(8.dp, 4.dp)
                                .height(14.dp)
                        )
                        Text(
                            text = "高鐵車票",
                            color = black600
                        )
                    }


                    Text(
                        text = "2025/12/25 19:25",
                        color = black600
                    )

                }
                Column() {
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {


                        Column(
                            horizontalAlignment = Alignment.End,


                            ) {
                            Text(
                                text = "5,000",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.ExtraBold,
                                textAlign = TextAlign.End,
                                modifier = Modifier.padding(0.dp,0.dp,0.dp,4.dp)

                            )
                            Text(
                                text = "=1,000",
                                fontSize = 15.sp,
                                color = black600,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.End

                            )


                        }


                        Text(
                            text = "JPY",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 24.sp,
                            modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)
                        )


                    }
                    Text(
                        text = "Share People more +",
                        fontSize = 14.sp,
                        lineHeight = 24.sp,

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp,4.dp,0.dp,0.dp),
                        textAlign = TextAlign.End,
                    )
                }
            }
        }
        HorizontalDivider(
            thickness = 1.dp,
            color = white400,
            modifier = Modifier.padding(0.dp,12.dp)
        )
        Column(
            modifier = Modifier
                .padding(20.dp, 0.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_member_01),
                    contentDescription = "member icon",
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp)
                )
                Column(

                ) {
                    Text(
                        text = "Rubyyyyyer",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Row(
                        modifier = Modifier.padding(0.dp, 4.dp),
                    ) {
                        Text(
                            text = "交通",
                            color = black600,
                        )
                        VerticalDivider(
                            thickness = 2.dp,
                            color = black500,
                            modifier = Modifier
                                .padding(8.dp, 4.dp)
                                .height(14.dp)
                        )
                        Text(
                            text = "高鐵車票",
                            color = black600
                        )
                    }


                    Text(
                        text = "2025/12/25 19:25",
                        color = black600
                    )

                }
                Column() {
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {


                        Column(
                            horizontalAlignment = Alignment.End,


                            ) {
                            Text(
                                text = "5,000",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.ExtraBold,
                                textAlign = TextAlign.End,
                                modifier = Modifier.padding(0.dp,0.dp,0.dp,4.dp)

                            )
                            Text(
                                text = "=1,000",
                                fontSize = 15.sp,
                                color = black600,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.End

                            )


                        }


                        Text(
                            text = "JPY",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 24.sp,
                            modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)
                        )


                    }
                    Text(
                        text = "Share People more +",
                        fontSize = 14.sp,
                        lineHeight = 24.sp,

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp,4.dp,0.dp,0.dp),
                        textAlign = TextAlign.End,
                    )
                }
            }
        }
        HorizontalDivider(
            thickness = 1.dp,
            color = white400,
            modifier = Modifier.padding(0.dp,12.dp)
        )
        Column(
            modifier = Modifier
                .padding(20.dp, 0.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_member_01),
                    contentDescription = "member icon",
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp)
                )
                Column(

                ) {
                    Text(
                        text = "Rubyyyyyer",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Row(
                        modifier = Modifier.padding(0.dp, 4.dp),
                    ) {
                        Text(
                            text = "交通",
                            color = black600,
                        )
                        VerticalDivider(
                            thickness = 2.dp,
                            color = black500,
                            modifier = Modifier
                                .padding(8.dp, 4.dp)
                                .height(14.dp)
                        )
                        Text(
                            text = "高鐵車票",
                            color = black600
                        )
                    }


                    Text(
                        text = "2025/12/25 19:25",
                        color = black600
                    )

                }
                Column() {
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {


                        Column(
                            horizontalAlignment = Alignment.End,


                            ) {
                            Text(
                                text = "5,000",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.ExtraBold,
                                textAlign = TextAlign.End,
                                modifier = Modifier.padding(0.dp,0.dp,0.dp,4.dp)

                            )
                            Text(
                                text = "=1,000",
                                fontSize = 15.sp,
                                color = black600,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.End

                            )


                        }


                        Text(
                            text = "JPY",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 24.sp,
                            modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)
                        )


                    }
                    Text(
                        text = "Share People more +",
                        fontSize = 14.sp,
                        lineHeight = 24.sp,

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp,4.dp,0.dp,0.dp),
                        textAlign = TextAlign.End,
                    )
                }
            }
        }
        HorizontalDivider(
            thickness = 1.dp,
            color = white400,
            modifier = Modifier.padding(0.dp,12.dp)
        )
        Column(
            modifier = Modifier
                .padding(20.dp, 0.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_member_01),
                    contentDescription = "member icon",
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp)
                )
                Column(

                ) {
                    Text(
                        text = "Rubyyyyyer",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Row(
                        modifier = Modifier.padding(0.dp, 4.dp),
                    ) {
                        Text(
                            text = "交通",
                            color = black600,
                        )
                        VerticalDivider(
                            thickness = 2.dp,
                            color = black500,
                            modifier = Modifier
                                .padding(8.dp, 4.dp)
                                .height(14.dp)
                        )
                        Text(
                            text = "高鐵車票",
                            color = black600
                        )
                    }


                    Text(
                        text = "2025/12/25 19:25",
                        color = black600
                    )

                }
                Column() {
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {


                        Column(
                            horizontalAlignment = Alignment.End,


                            ) {
                            Text(
                                text = "5,000",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.ExtraBold,
                                textAlign = TextAlign.End,
                                modifier = Modifier.padding(0.dp,0.dp,0.dp,4.dp)

                            )
                            Text(
                                text = "=1,000",
                                fontSize = 15.sp,
                                color = black600,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.End

                            )


                        }


                        Text(
                            text = "JPY",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 24.sp,
                            modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)
                        )


                    }
                    Text(
                        text = "Share People more +",
                        fontSize = 14.sp,
                        lineHeight = 24.sp,

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp,4.dp,0.dp,0.dp),
                        textAlign = TextAlign.End,
                    )
                }
            }
        }
        HorizontalDivider(
            thickness = 1.dp,
            color = white400,
            modifier = Modifier.padding(0.dp,12.dp)
        )
        Column(
            modifier = Modifier
                .padding(20.dp, 0.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_member_01),
                    contentDescription = "member icon",
                    modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp)
                )
                Column(

                ) {
                    Text(
                        text = "Rubyyyyyer",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Row(
                        modifier = Modifier.padding(0.dp, 4.dp),
                    ) {
                        Text(
                            text = "交通",
                            color = black600,
                        )
                        VerticalDivider(
                            thickness = 2.dp,
                            color = black500,
                            modifier = Modifier
                                .padding(8.dp, 4.dp)
                                .height(14.dp)
                        )
                        Text(
                            text = "高鐵車票",
                            color = black600
                        )
                    }


                    Text(
                        text = "2025/12/25 19:25",
                        color = black600
                    )

                }
                Column() {
                    Row(
                        horizontalArrangement = Arrangement.End,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {


                        Column(
                            horizontalAlignment = Alignment.End,


                            ) {
                            Text(
                                text = "5,000",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.ExtraBold,
                                textAlign = TextAlign.End,
                                modifier = Modifier.padding(0.dp,0.dp,0.dp,4.dp)

                            )
                            Text(
                                text = "=1,000",
                                fontSize = 15.sp,
                                color = black600,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.End

                            )


                        }


                        Text(
                            text = "JPY",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            lineHeight = 24.sp,
                            modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp)
                        )


                    }
                    Text(
                        text = "Share People more +",
                        fontSize = 14.sp,
                        lineHeight = 24.sp,

                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp,4.dp,0.dp,0.dp),
                        textAlign = TextAlign.End,
                    )
                }
            }
        }
        HorizontalDivider(
            thickness = 1.dp,
            color = white400,
            modifier = Modifier.padding(0.dp,12.dp)
        )
//copy------------------------------------------------------------------------





    }
}

@Preview
@Composable
fun tripAPre() {
    tripA()
}