package com.example.findyourway.Login

import android.content.Intent
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.findyourway.CustomComposes.CustomButton
import com.example.findyourway.MainActivity
import com.example.findyourway.Navigation.Screen
import com.example.findyourway.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun NewAccountPage(navHostController: NavHostController) {

    val firebaseAuth = Firebase.auth
    val quickSand = FontFamily(Font(R.font.quicksand_medium))
    var username by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
            value = username,
            onValueChange = { username = it },
            modifier = Modifier.width(265.dp),
            placeholder = {
                Text(
                    text = "Username",
                    color = Color.White,
                    fontFamily = quickSand
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.White,
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = dob,
            onValueChange = { dob = it },
            modifier = Modifier.width(265.dp),
            placeholder = {
                Text(
                    text = "DOB",
                    color = Color.White,
                    fontFamily = quickSand
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.White,
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = location,
            onValueChange = { location = it },
            modifier = Modifier.width(265.dp),
            placeholder = {
                Text(
                    text = "Location",
                    color = Color.White,
                    fontFamily = quickSand
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.White,
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.width(265.dp),
            placeholder = {
                Text(
                    text = "Email",
                    color = Color.White,
                    fontFamily = quickSand
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.White,
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.width(265.dp),
            placeholder = {
                Text(
                    text = "Password",
                    color = Color.White,
                    fontFamily = quickSand
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = Color.White,
            )
        )
        Spacer(modifier = Modifier.height(15.dp))
        Button(
            onClick = {
                firebaseAuth
                    .createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener {
                        val currentUser = FirebaseAuth.getInstance().currentUser!!
                        val profileUpdates = UserProfileChangeRequest.Builder()
                            .setDisplayName(username).build()
                        currentUser.updateProfile(profileUpdates)
                        context.startActivity(Intent(context, MainActivity::class.java))
                    }
                      },
            modifier = Modifier.width(265.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xff05668D))
        ) {
            Text(
                text = "CONTINUE",
                fontSize = 15.sp,
                fontFamily = quickSand,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(13.dp))
        Divider(
            thickness = 1.dp,
            color = Color.White,
            modifier = Modifier.width(265.dp)
        )
        Spacer(modifier = Modifier.height(13.dp))
        CustomButton(text = "Sign in with Google", icon = R.drawable.google_logo) {

        }
        Spacer(modifier = Modifier.height(15.dp))
        CustomButton(text = "Sign in with Facebook", icon = R.drawable.facebook_logo) {

        }
        TextButton(
            onClick = { navHostController.navigate(Screen.LoginPage.route) },
            colors = ButtonDefaults.textButtonColors(contentColor = Color.White)
        ) {
            Text(text = "Already have and account? Sign in", fontFamily = quickSand)
        }
    }

}