package com.example.findyourway.HomePage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
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
fun Feeds() {

    val quicksand = FontFamily(Font(R.font.quicksand_medium))

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(backgroundColor = Color.DarkGray) {
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = "Feed",
                color = Color.White,
                fontFamily = quicksand,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.weight(0.5f))
            IconButton(onClick = { /*TODO*/ }) {
                Image(painter = painterResource(id = R.drawable.search_white), contentDescription = "" )
            }
            IconButton(onClick = { /*TODO*/ }) {
                Image(painter = painterResource(id = R.drawable.more_white), contentDescription = "" )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn( verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(3){
                Feed()
            }
        }
    }
}

@Composable
fun Feed() {
    Column(modifier = Modifier
        .fillMaxWidth()
        .heightIn(150.dp , 480.dp)
        .padding(horizontal = 20.dp)
        .shadow(elevation = 2.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, top = 15.dp)) {
            Image(
                painter = painterResource(id = R.drawable.person_white),
                contentDescription = "",
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(50))
                    .background(Color.Red)
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(text = "Filia Amora")
                Text(text = "15 minutes ago")
            }
            Spacer(modifier = Modifier.weight(0.5f))
            IconButton(onClick = { /*TODO*/ }) {
                Image(painter = painterResource(id = R.drawable.share_black), contentDescription = "")
            }
            IconButton(onClick = { /*TODO*/ }) {
                Image(painter = painterResource(id = R.drawable.more_black), contentDescription = "")
            }
        }
        Text(text = "Collected Taj Mahal Scroll", modifier = Modifier.padding(start = 15.dp))
        Column(Modifier.fillMaxWidth().heightIn(min = 0.dp,max = 200.dp)) {
            Image(
                painter = painterResource(id = R.drawable.tajmahal),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(end = 20.dp)) {
            Spacer(modifier = Modifier.weight(0.5f))
            IconButton(onClick = { /*TODO*/ }) {
                Image(painter = painterResource(id = R.drawable.share_gray), contentDescription = "")
            }
            Text(text = "2")
            IconButton(onClick = { /*TODO*/ }) {
                Image(painter = painterResource(id = R.drawable.heart_gray), contentDescription = "")
            }
            Text(text = "2")
        }
    }
}