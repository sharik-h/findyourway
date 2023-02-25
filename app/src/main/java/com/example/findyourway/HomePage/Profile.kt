package com.example.findyourway.HomePage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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

@Preview
@Composable
fun Profile() {

    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xFFFFEBEB))
    ) {
        val quicksand = FontFamily(Font(R.font.quicksand_medium))
        TopAppBar(backgroundColor = Color.DarkGray) {
            Text(
                text = "Profile",
                fontFamily = quicksand,
                color = Color.White,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.weight(0.5f))
            IconButton(onClick = { /*TODO*/ }) {
                Image(painter = painterResource(id = R.drawable.more_white), contentDescription = "")
            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
                    .shadow(elevation = 3.dp)
                    .background(Color.White)
            ) {
                Row(Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.weight(0.5f))
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(painter = painterResource(id = R.drawable.settings_gray), contentDescription = "")
                    }
                }
                Row(Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier.weight(0.5f),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.person_white),
                            contentDescription = "",
                            modifier = Modifier
                                .size(70.dp)
                                .clip(RoundedCornerShape(50))
                                .background(Color(0xC351D6FF))
                        )
                        Text(text = "@sunnytravels", fontFamily = quicksand, color = Color.DarkGray)
                    }
                    Column(Modifier.weight(0.5f)) {
                        Text(text = "Joined Feb 2023", fontFamily = quicksand, fontSize = 20.sp)
                        Text(text = "4 Freinds Connected", fontFamily = quicksand, fontSize = 15.sp)
                        Button(
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff05668D))
                        ) {
                            Text(text = "ADD FRIEND +", fontFamily = quicksand, color = Color.White)
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .size(220.dp)
                    .shadow(elevation = 3.dp)
                    .background(Color.White)
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Row(Modifier.fillMaxWidth()){
                    Text(text = "General", fontFamily = quicksand, fontSize = 20.sp)
                    Spacer(modifier = Modifier.weight(0.5f))
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(painter = painterResource(id = R.drawable.edit_gray), contentDescription = "")
                    }
                }
                Text(text = "DOB             12-12-1993", fontFamily = quicksand, fontSize = 18.sp)
                Text(text = "Email           sunny@traveller.com", fontFamily = quicksand, fontSize = 18.sp)
                Text(text = "Location      Kolkata", fontFamily = quicksand, fontSize = 18.sp)
                Text(text = "Password     **************", fontFamily = quicksand, fontSize = 18.sp)
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()

                    .shadow(elevation = 3.dp)
                    .background(Color.White)
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Row(Modifier.fillMaxWidth()){
                    Text(text = "Friends", fontFamily = quicksand, fontSize = 20.sp)
                    Spacer(modifier = Modifier.weight(0.5f))
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(painter = painterResource(id = R.drawable.add_gray), contentDescription = "")
                    }
                }
                LazyColumn{
                    items(4){
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.person_white),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(RoundedCornerShape(50))
                                    .background(Color(0xC351D6FF))
                            )
                            Spacer(modifier = Modifier.width(50.dp))
                            Text(text = "Friend$it", fontFamily = quicksand, )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }
            }
        }
    }
}