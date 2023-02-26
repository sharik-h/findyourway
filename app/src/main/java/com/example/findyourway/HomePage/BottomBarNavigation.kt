package com.example.findyourway.HomePage

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.findyourway.ViewModel.viewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun BottomBarNavigation(navHostController: NavHostController, viewModel: viewModel) {
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
            Scrolls(viewModel = viewModel)
        }
        composable(route = BottomBarScreen.Leaderboard.route){

        }
        composable(route = BottomBarScreen.Profile.route){
            Profile()
        }
    }
}