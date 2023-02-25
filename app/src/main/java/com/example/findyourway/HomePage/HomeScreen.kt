package com.example.findyourway.HomePage

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

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
        BottomBarScreen.Profile,
        BottomBarScreen.Leaderboard,
        BottomBarScreen.Scrolls,
        BottomBarScreen.Dashboard,
        BottomBarScreen.Feed
    )
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    
    BottomNavigation {
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
    BottomNavigationItem(
        label = {
                Text(text = screen.title)
        },
        icon = {
               Icon(painter = painterResource(id = screen.icon), contentDescription = "")
        },
        selected = currentDestinatoin?.hierarchy?.any {
            it.route == screen.route
        }== true,
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navHostController.navigate(screen.route){
                popUpTo(navHostController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}