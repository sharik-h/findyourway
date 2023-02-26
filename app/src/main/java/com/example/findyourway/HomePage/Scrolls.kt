package com.example.findyourway.HomePage

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.findyourway.R
import com.example.findyourway.local
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@SuppressLint("MissingPermission")
@Composable
fun Scrolls() {

    val quicksand = FontFamily(Font(R.font.quicksand_medium))
    var search by remember { mutableStateOf("") }
    var addScrollVisible by remember { mutableStateOf(false) }
    var newScrollVisible by remember { mutableStateOf(false) }
    var viewScrollVisible by remember { mutableStateOf(false) }
    if (addScrollVisible){
        Dialog(onDismissRequest = {addScrollVisible = !addScrollVisible}) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(vertical = 55.dp)
                    .background(Color.White)) {
                Row(Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.weight(0.5f))
                    IconButton(onClick = { addScrollVisible = false }) {
                        Image(painter = painterResource(id = R.drawable.close_gray), contentDescription = "")
                    }
                }
                AddScroll()
            }
        }
    }
    if (newScrollVisible) {
        Dialog(onDismissRequest = { newScrollVisible = !newScrollVisible }) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(vertical = 35.dp)
                    .background(Color.White)) {
                Row(Modifier.fillMaxWidth()) {
                    Spacer(modifier = Modifier.weight(0.5f))
                    IconButton(onClick = { newScrollVisible = false }) {
                        Image(painter = painterResource(id = R.drawable.close_gray), contentDescription = "")
                    }
                }
                ScrollCapture()
            }
        }
    }
    if (viewScrollVisible){
        Dialog(onDismissRequest = { viewScrollVisible = !viewScrollVisible }) {
            Row(Modifier.fillMaxWidth()) {
                Spacer(modifier = Modifier
                    .weight(0.5f)
                    .padding(vertical = 50.dp))
                IconButton(onClick = { viewScrollVisible = false }) {
                    Image(painter = painterResource(id = R.drawable.close_gray), contentDescription = "")
                }
            }
            ViewScroll()
        }
    }

    Column(Modifier.fillMaxSize()) {


        val cameraPosition = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(LatLng(local.latitude, local.longitude),15f)
        }

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPosition
        )
    }
    Column(modifier = Modifier.fillMaxSize()) {

        TopAppBar(backgroundColor = Color.DarkGray) {
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = "Scrolls",
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
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(top = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            TextField(
                value = search,
                onValueChange = { search = it },
                placeholder = {
                    Text(text = "Search", fontFamily = quicksand, color = Color.Black)
                },
                trailingIcon = {
                    Icon(painter = painterResource(id = R.drawable.search_black), contentDescription = "")
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.White,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,

                ),
                modifier = Modifier
                    .shadow(elevation = 3.dp)
                    .height(60.dp),
            )
        }
        Spacer(modifier = Modifier.weight(0.5f))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 10.dp)) {
            Spacer(modifier = Modifier.weight(0.5f))
            FloatingActionButton(onClick = { addScrollVisible = true }, backgroundColor = Color.Black) {
                Image(painter = painterResource(id = R.drawable.add_white), contentDescription = "")
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 10.dp)) {
            Spacer(modifier = Modifier.weight(0.5f))
            FloatingActionButton(onClick = { newScrollVisible = true }, backgroundColor = Color.Black) {
                Image(painter = painterResource(id = R.drawable.capture_white), contentDescription = "")
            }
        }
        Spacer(modifier = Modifier.height(70.dp))
    }
}

@Composable
fun AddScroll() {

    val quicksand = FontFamily(Font(R.font.quicksand_medium))
    var name by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)) {
        Text(text = "Add Scroll", fontFamily = quicksand , fontSize = 30.sp)
        Text(text = "New Scroll Creation", fontFamily = quicksand)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Name", fontFamily = quicksand)
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                          Text(text = "Name of the scroll", fontFamily = quicksand)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Black,
                focusedBorderColor = Color.Black,
                cursorColor = Color.Black
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Location", fontFamily = quicksand)
        Row(Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = location,
                onValueChange = { location = it },
                placeholder = {
                    Text(text = "GPS cordinates", fontFamily = quicksand)
                },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.Black,
                    focusedBorderColor = Color.Black,
                    cursorColor = Color.Black
                ),
                modifier = Modifier.weight(0.5f)
            )
            IconButton(onClick = { /*TODO*/ }, modifier = Modifier.weight(0.1f)) {
                Image(painter = painterResource(id = R.drawable.location_gray), contentDescription = "")
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Description", fontFamily = quicksand)
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            placeholder = {
                Text(text = "Description", fontFamily = quicksand)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Black,
                focusedBorderColor = Color.Black,
                cursorColor = Color.Black
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff05668D)),
        ) {
            Text(text = "Save", fontFamily = quicksand, color = Color.White)
        }
    }
}

@Composable
fun ScrollCapture() {

    val quicksand = FontFamily(Font(R.font.quicksand_medium))
    var description by remember { mutableStateOf("") }
    var image by remember { mutableStateOf<Painter?>(null) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp))
    {
        Text(text = "Scroll Capture", fontFamily = quicksand , fontSize = 30.sp)
        Text(text = "Taj Mahal Scroll", fontFamily = quicksand, fontSize = 17.sp)
        Text(text = "27.1750.78.04215", fontFamily = quicksand, fontSize = 17.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Photo", fontFamily = quicksand)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(158.dp)
                .border(color = Color.Black, shape = RectangleShape, width = 0.5.dp),
            contentAlignment = Alignment.Center
        ) {
            if (image != null){
                Image(painter = image!!, contentDescription = "", modifier = Modifier.fillMaxSize())
            }else{
                Image(
                    painter = painterResource(id = R.drawable.image_icon_gray),
                    contentDescription = "",
                    modifier = Modifier.size(70.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Description", fontFamily = quicksand)
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            placeholder = {
                Text(text = "Description", fontFamily = quicksand)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.Black,
                focusedBorderColor = Color.Black,
                cursorColor = Color.Black
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff05668D)),
        ) {
            Text(text = "Upload", fontFamily = quicksand, color = Color.White)
        }
    }
}

@Composable
fun ViewScroll() {
    val quicksand = FontFamily(Font(R.font.quicksand_medium))
    var description by remember { mutableStateOf("") }
    var image by remember { mutableStateOf<Painter?>(null) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp))
    {
        Text(text = "Taj Mahal Scroll", fontFamily = quicksand, fontSize = 30.sp)
        Text(text = "27.1750.78.04215", fontFamily = quicksand, fontSize = 17.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Column(
            Modifier
                .fillMaxWidth()
                .heightIn(min = 0.dp, max = 200.dp)) {
            Image(
                painter = painterResource(id = R.drawable.tajmahal),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Description", fontFamily = quicksand)
        Text(
            text = "The Taj Mahal (/ˌtɑːdʒ məˈhɑːl, ˌtɑːʒ-/; lit. 'Crown of the Palace')[4][5][6] is an ivory-white marble mausoleum on the right bank of the river Yamuna in the Indian city of Agra. It was commissioned in 1631 by the Mughal emperor Shah Jahan (r. 1628–1658) to house the tomb of his favourite wife, Mumtaz Mahal; it also houses the tomb of Shah Jahan himself. The tomb is the centrepiece of a 17-hectare (42-acre) complex, which includes a mosque and a guest house, and is set in formal gardens bounded on three sides by a crenellated wall.\n" +
                    "Construction of the mausoleum was essentially completed in 1643, but work continued on other phases of the project for another 10 years. The Taj Mahal complex is believed to have been completed in its entirety in 1653 at a cost estimated at the time to be around ₹32 million, which in 2020 would be approximately ₹70 billion (about US \$1 billion). The construction project employed some 20,000 artisans under the guidance of a board of architects led by Ustad Ahmad Lahauri, the emperor’s court architect. Various types of symbolism have been employed in the Taj to reflect natural beauty and divinity.\n" +
                    "The Taj Mahal was designated as a UNESCO World Heritage Site in 1983 for being \"the jewel of Muslim art in India and one of the universally admired masterpieces of the world's heritage\". It is regarded by many as the best example of Mughal architecture and a symbol of India's rich history. The Taj Mahal attracts more than 6 million visitors a year[3] and in 2007, it was declared a winner of the New 7 Wonders of the World (2000–2007) initiative.",
            fontFamily = quicksand
        )
    }
    
}