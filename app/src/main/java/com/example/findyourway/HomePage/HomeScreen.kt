package com.example.findyourway.HomePage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.findyourway.R

@Composable
fun HomeScreen(navHostController: NavHostController) {
    Scaffold(
        bottomBar = { BottomBar(navHostController = navHostController) }
    ) { it->
        val i = it
       BottomBarNavigation(navHostController = navHostController)
    }
}

@Composable
fun BottomBar(navHostController: NavHostController) {
    val screns = listOf(
        BottomBarScreen.Feed,
        BottomBarScreen.Dashboard,
        BottomBarScreen.Scrolls,
        BottomBarScreen.Leaderboard,
        BottomBarScreen.Profile
    )
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    
    BottomNavigation(backgroundColor = Color.Black) {
        screns.forEach { screen ->
            AddItem(screen = screen, currentDestinatoin = currentDestination, navHostController = navHostController)
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestinatoin: NavDestination?,
    navHostController: NavHostController
) {
    val quicksand = FontFamily(Font(R.font.quicksand_medium))
    BottomNavigationItem(
        label = {
                Text(text = screen.title, color = Color.White, fontFamily = quicksand)
        },
        icon = {
               Image(painter = painterResource(id = screen.icon), contentDescription = "")
        },
        selected = currentDestinatoin?.hierarchy?.any {
            it.route == screen.route
        }== true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = 0f),
        onClick = {
            navHostController.navigate(screen.route){
                popUpTo(navHostController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}