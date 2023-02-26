package com.example.findyourway.HomePage

import android.annotation.SuppressLint
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.annotation.RequiresApi
import androidx.compose.foundation.*
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
import coil.compose.rememberAsyncImagePainter
import com.example.findyourway.ViewModel.viewModel
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.findyourway.R
import com.example.findyourway.local
import com.google.android.gms.location.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("MissingPermission")
@Composable
fun Scrolls(viewModel: viewModel) {

    viewModel.getAllScroll()
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
                AddScroll(viewModel = viewModel){
                    viewModel.addNewScroll()
                    addScrollVisible = false
                }
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
                ScrollCapture(viewModel = viewModel){
                    newScrollVisible = false
                }
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
            ViewScroll(viewModel = viewModel)
        }
    }

    Column(Modifier.fillMaxSize()) {


        val cameraPosition = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(LatLng(local.latitude, local.longitude),15f)
        }

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPosition
        ){
            viewModel.allScrolls.forEach {item ->
                val loc = item.location.split(",")
                var cordinates  by remember { mutableStateOf( LatLng(loc[0].toDouble(), loc[1].toDouble()))}
                Marker (
                    position = cordinates,
                    title = item.name,
                    draggable = true,
                    onInfoWindowClick = {
                        viewModel.updateData("selname", item.name)
                        viewModel.updateData("selpos", item.location)
                        viewModel.updateData("seldesc", item.description) },
                )
            }
        }
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
            FloatingActionButton(onClick = {
                newScrollVisible = true
                viewModel.searchScroll()
                                           },
                backgroundColor = Color.Black) {
                Image(painter = painterResource(id = R.drawable.capture_white), contentDescription = "")
            }
        }
        Spacer(modifier = Modifier.height(70.dp))
    }
}

@Composable
fun AddScroll(viewModel: viewModel, onclick:() -> Unit ) {

    val quicksand = FontFamily(Font(R.font.quicksand_medium))
    val newScroll by viewModel.newScroll
    val scrollState = rememberScrollState()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)
        .verticalScroll(scrollState)) {
        Text(text = "Add Scroll", fontFamily = quicksand , fontSize = 30.sp)
        Text(text = "New Scroll Creation", fontFamily = quicksand)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Name", fontFamily = quicksand)
        OutlinedTextField(
            value = newScroll.name,
            onValueChange = { viewModel.updateData("nameScroll", it)},
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
                value = newScroll.location,
                onValueChange = { viewModel.updateData("locationScroll", it) },
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
            IconButton(
                onClick = { viewModel.updateData("locationScroll", "${local.latitude},${local.longitude}") },
                modifier = Modifier
                    .weight(0.1f)
                    .shadow(elevation = 3.dp)
            ) {
                Image(painter = painterResource(id = R.drawable.location_gray), contentDescription = "")
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Description", fontFamily = quicksand)
        OutlinedTextField(
            value = newScroll.description,
            onValueChange = { viewModel.updateData("descScroll", it) },
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
            onClick = { onclick() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff05668D)),
        ) {
            Text(text = "Save", fontFamily = quicksand, color = Color.White)
        }
    }
}

@Composable
fun ScrollCapture(viewModel: viewModel, onclick: () -> Unit) {

    val quicksand = FontFamily(Font(R.font.quicksand_medium))
    var description by remember { mutableStateOf("") }
    var image by remember { mutableStateOf<Painter?>(null) }
    val newPost by viewModel.newPost
    val selectedPost by viewModel.selectedScroll
    val bitimg  by viewModel.imageBitmap
    val cLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicturePreview()) { bitmap ->
        if (bitmap != null) {
            viewModel.setbitimgValue(bitmap)
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp))
    {
        Text(text = "Scroll Capture", fontFamily = quicksand , fontSize = 30.sp)
        Text(text = selectedPost.name, fontFamily = quicksand, fontSize = 17.sp)
        Text(text = selectedPost.location, fontFamily = quicksand, fontSize = 17.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Photo", fontFamily = quicksand)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(158.dp)
                .border(color = Color.Black, shape = RectangleShape, width = 0.5.dp)
                .clickable {
                    if (bitimg == null) {
                        cLauncher.launch()
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            if (image != null){
                Image(
                    painter = rememberAsyncImagePainter(bitimg),
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize()
                )
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
            value = newPost.Description,
            onValueChange = { viewModel.updateData("postDesc", it) },
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
            onClick = { onclick() },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff05668D)),
        ) {
            Text(text = "Upload", fontFamily = quicksand, color = Color.White)
        }
    }
}

@Composable
fun ViewScroll(viewModel: viewModel) {
    val quicksand = FontFamily(Font(R.font.quicksand_medium))
    var description by remember { mutableStateOf("") }
    var image by remember { mutableStateOf<Painter?>(null) }
    val scrollData by viewModel.scrollData
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