package com.example.findyourway

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.findyourway.Login.LoginPage
import com.example.findyourway.Navigation.NavGraph
import com.example.findyourway.ui.theme.FindyourwayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FindyourwayTheme {
                val navContoller = rememberNavController()
                NavGraph(navHostController = navContoller)
            }
        }
    }
}