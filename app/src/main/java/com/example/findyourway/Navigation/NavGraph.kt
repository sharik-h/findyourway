package com.example.findyourway.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.findyourway.Login.LoginPage
import com.example.findyourway.Login.NewAccountPage

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.LoginPage.route
    ){
        composable(route = Screen.LoginPage.route){
            LoginPage(navHostController = navHostController)
        }
        composable(route = Screen.NewAccountPage.route){
            NewAccountPage(navHostController = navHostController)
        }
    }
}