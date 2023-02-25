package com.example.findyourway.Navigation

sealed class Screen(val route: String) {
    object LoginPage: Screen(route = "loginPage")
    object NewAccountPage: Screen(route = "newAccountPage")
}