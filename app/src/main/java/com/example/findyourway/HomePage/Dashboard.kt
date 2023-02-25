package com.example.findyourway.HomePage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.findyourway.R

@Preview(showBackground = true)
@Composable
fun Dashboard() {

    val quicksand = FontFamily(Font(R.font.quicksand_medium))
    val placesVisited = listOf("kerala", "Banglore", "TamilNadu")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF5F5))
    ) {
        TopAppBar(backgroundColor = Color.DarkGray) {
            Text(
                text = "Dashboard",
                color = Color.White,
                fontFamily = quicksand,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.weight(0.5f))
            IconButton(onClick = { /*TODO*/ }) {
                Image(
                    painter = painterResource(id = R.drawable.more_white),
                    contentDescription = ""
                )
            }
        }

        Column(
            Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .shadow(elevation = 3.dp)
                    .background(Color.White)
            )
            {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.weight(0.5f)
                ) {
                    Text(text = "3", fontFamily = quicksand, fontSize = 40.sp)
                    Text(text = "Ranking", fontFamily = quicksand, fontSize = 20.sp)
                }
                Column(
                    modifier = Modifier.weight(0.5f),
                    verticalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Text(text = "@sunnytravels", color = Color.DarkGray, fontFamily = quicksand)
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(text = "Badges Earned")
                    Spacer(modifier = Modifier.height(5.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                        Image(
                            painter = painterResource(id = R.drawable.star_green),
                            contentDescription = "",
                            Modifier
                                .clip(RoundedCornerShape(50))
                                .background(Color(0xC351FF62))
                                .size(30.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.star_red),
                            contentDescription = "",
                            Modifier
                                .clip(RoundedCornerShape(50))
                                .background(Color(0xC3FF5151))
                                .size(30.dp)
                        )
                        Image(
                            painter = painterResource(id = R.drawable.star_blue),
                            contentDescription = "",
                            Modifier
                                .clip(RoundedCornerShape(50))
                                .background(Color(0xC351D6FF))
                                .size(30.dp)
                        )
                    }
                }
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            )
            {
                Column(
                    modifier = Modifier
                        .weight(0.5f)
                        .shadow(elevation = 3.dp)
                        .background(Color.White),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "9", fontFamily = quicksand, fontSize = 40.sp)
                    Text(text = "  Scrolls\nCollected", fontFamily = quicksand, fontSize = 20.sp)
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "Show more", color = Color.Black, fontFamily = quicksand)
                        Image(
                            painter = painterResource(id = R.drawable.arrow_round_gray),
                            contentDescription = ""
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .weight(0.5f)
                        .shadow(elevation = 3.dp)
                        .background(Color.White),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "2", fontFamily = quicksand, fontSize = 40.sp)
                    Text(text = "  Scrolls \nUploaded", fontFamily = quicksand, fontSize = 20.sp)
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "Show more", color = Color.Black, fontFamily = quicksand)
                        Image(
                            painter = painterResource(id = R.drawable.arrow_round_gray),
                            contentDescription = ""
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            )
            {
                Column(
                    modifier = Modifier
                        .weight(0.5f)
                        .shadow(elevation = 3.dp)
                        .background(Color.White),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "3", fontFamily = quicksand, fontSize = 40.sp)
                    Text(text = "Territories\n travelled", fontFamily = quicksand, fontSize = 20.sp)
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "Show more", color = Color.Black, fontFamily = quicksand)
                        Image(
                            painter = painterResource(id = R.drawable.arrow_round_gray),
                            contentDescription = ""
                        )
                    }
                }
                Column(
                    modifier = Modifier
                        .weight(0.5f)
                        .shadow(elevation = 3.dp)
                        .background(Color.White),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "4", fontFamily = quicksand, fontSize = 40.sp)
                    Text(text = "   Friends\nConnected", fontFamily = quicksand, fontSize = 20.sp)
                    TextButton(onClick = { /*TODO*/ }) {
                        Text(text = "Show more", color = Color.Black, fontFamily = quicksand)
                        Image(
                            painter = painterResource(id = R.drawable.arrow_round_gray),
                            contentDescription = ""
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(elevation = 3.dp)
                    .background(Color.White)
                    .padding(20.dp)
            ) {
                Text(text = "Top Territories", fontSize = 20.sp, fontFamily = quicksand)
                placesVisited.forEach {
                    Text(text = it, fontFamily = quicksand, fontSize = 15.sp)
                }
                TextButton(onClick = { /*TODO*/ }) {
                    Text(text = "Show more", fontFamily = quicksand, color = Color.Black)
                    Image(
                        painter = painterResource(id = R.drawable.arrow_round_gray),
                        contentDescription = ""
                    )
                }
            }
        }
    }
}