package com.example.findyourway.HomePage

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun BottomBarNavigation(navHostController: NavHostController) {
    NavHost(
        startDestination = BottomBarScreen.Feed.route,
        navController = navHostController
    ){
        composable(route = BottomBarScreen.Feed.route){
            Feeds()
        }
        composable(route = BottomBarScreen.Dashboard.route){
            Dashboard()
        }
        composable(route = BottomBarScreen.Scrolls.route){

        }
        composable(route = BottomBarScreen.Leaderboard.route){

        }
        composable(route = BottomBarScreen.Profile.route){

        }
    }
}