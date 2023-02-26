package com.example.findyourway.HomePage

import com.example.findyourway.R

sealed class BottomBarScreen (
    val route: String,
    val title: String,
    val icon: Int,
){
    object Feed: BottomBarScreen(
        route = "feed",
        title = "Feed",
        icon = R.drawable.feed_white
    )
    object Dashboard: BottomBarScreen(
        route = "dashboard",
        title = "Dashb..",
        icon = R.drawable.dashboard_white
    )
    object Scrolls: BottomBarScreen(
        route = "scrolls",
        title = "Scrolls",
        icon = R.drawable.scroll_white
    )
    object Leaderboard: BottomBarScreen(
        route = "leaderboard",
        title = "Lead..",
        icon = R.drawable.leaderboard_white
    )
    object Profile: BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = R.drawable.person_white
    )
}