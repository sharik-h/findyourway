package com.example.findyourway.Login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.findyourway.CustomComposes.CustomButton
import com.example.findyourway.Navigation.Screen
import com.example.findyourway.R
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun LoginPage(navHostController: NavHostController) {

    val firebaseAuth = Firebase.auth
    var quickSand = FontFamily(Font(R.font.quicksand_medium))
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var selected by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logofyw),
            contentDescription = "",
            modifier = Modifier.size(200.dp)
        )
        Text(
            text = "Login or Sign up for free",
            color = Color.White,
            fontSize = 18.sp,
            fontFamily = quickSand
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = if (selected) password else email,
            onValueChange = { if (selected) password = it else email = it },
            modifier = Modifier.width(265.dp),
            placeholder = {
                Text(
                    text = if(selected)"Password" else "Email or Username",
                    color = Color.White,
                    fontFamily = quickSand
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.White,
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                if (!selected){
                    selected = !selected
                }else {
                    firebaseAuth
                        .signInWithEmailAndPassword(email, password)
                        .addOnSuccessListener {

                        }
                }
            },
            modifier = Modifier.width(265.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff05668D))
        ) {
           Text(
               text = if (selected)"SIGN IN" else "CONTINUE",
               fontSize = 15.sp,
               fontFamily = quickSand,
               color = Color.White
           )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Divider(
            thickness = 1.dp,
            color = Color.White,
            modifier = Modifier.width(265.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        CustomButton(text = "Sign in with Google", icon = R.drawable.google_logo) {

        }
        Spacer(modifier = Modifier.height(20.dp))
        CustomButton(text = "Sign in with Facebook", icon = R.drawable.facebook_logo) {

        }
        TextButton(
            onClick = { navHostController.navigate(Screen.NewAccountPage.route) },
            colors = ButtonDefaults.textButtonColors(contentColor = Color.White)
        ) {
            Text(text = "Sign up with your email", fontFamily = quickSand)
        }
    }

}