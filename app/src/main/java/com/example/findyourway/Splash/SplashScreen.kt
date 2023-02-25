package com.example.findyourway.Splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.findyourway.Navigation.Screen
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay
import com.example.findyourway.R


@Composable
fun SplashScreen(navHostController: NavHostController) {
    var startAnimState by remember { mutableStateOf(false) }
    val floatAsState = animateFloatAsState(
        targetValue = if(startAnimState) 1f else 0f,
        animationSpec = tween(1000)
    )
    LaunchedEffect(key1 = true ) {
        startAnimState = true
        val user = FirebaseAuth.getInstance().currentUser
        delay(1000)
        navHostController.popBackStack()
        if (user?.uid != null) {

        }else{
            navHostController.navigate(Screen.LoginPage.route)
        }
    }
    Splash(floatAsState.value)
}

@Composable
fun Splash(floatAsState: Float) {
    Column(modifier = Modifier
        .fillMaxSize()
        .alpha(alpha = floatAsState)
        .background(Color.DarkGray),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logofyw),
            contentDescription = "",
            modifier = Modifier.size(280.dp)
        )
    }

}