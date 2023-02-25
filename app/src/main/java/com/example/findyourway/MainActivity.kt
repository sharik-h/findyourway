package com.example.findyourway

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.findyourway.HomePage.HomeScreen
import com.example.findyourway.Navigation.NavGraph
import com.example.findyourway.ui.theme.FindyourwayTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : ComponentActivity() {

    var firebaseUser: FirebaseUser? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseUser = FirebaseAuth.getInstance().currentUser

        setContent {
            FindyourwayTheme {
                val navContoller = rememberNavController()
                if (firebaseUser != null){
                    HomeScreen(navHostController = navContoller)
                }else{
                    NavGraph(navHostController = navContoller)
                }
            }
        }
    }
}