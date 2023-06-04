package com.example.timej.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.timej.R
import com.example.timej.ui.theme.HawkesBlue
import com.example.timej.ui.theme.Shark
import com.example.timej.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeacherNavBar(
    navController: NavController,
    name: String,
    content: @Composable () -> Unit
) {
    Scaffold(
        containerColor = White,
        bottomBar = {
            NavigationBar(
                contentColor = White,
                containerColor = White
            ) {
                NavBarItem(
                    navController = navController,
                    route = "",
                    painter = painterResource(id = R.drawable.icon_calendar),
                    text = stringResource(id = R.string.date),
                    name = name
                )
                NavBarItem(
                    navController = navController,
                    route = "teacher_schedule/$name",
                    painter = painterResource(id = R.drawable.ic_clock),
                    text = stringResource(id = R.string.today),
                    name = name
                )
                NavBarItem(
                    navController = navController,
                    route = "",
                    painter = painterResource(id = R.drawable.ic_week),
                    text = stringResource(id = R.string.week),
                    name = name
                )
                NavBarItem(
                    navController = navController,
                    route = "teacher_profile",
                    painter = painterResource(id = R.drawable.ic_menu),
                    text = stringResource(id = R.string.menu),
                    name = name
                )
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            content.invoke()
        }
    }
}

@Composable
private fun RowScope.NavBarItem(
    navController: NavController,
    route: String,
    painter: Painter,
    text: String,
    name: String
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBarItem(
        icon = {
            Box(
                modifier = Modifier.size(28.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painter,
                    contentDescription = null
                )
            }
        },
        label = {
            Text(
                text = text,
                style = com.example.timej.ui.theme.NavBarItem
            )
        },
        selected = currentDestination?.hierarchy?.any { it.route == route } == true,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = HawkesBlue,
            selectedTextColor = HawkesBlue,
            indicatorColor = White,
            unselectedIconColor = Shark,
            unselectedTextColor = Shark,
        ),
        onClick = {
            navController.navigate(route) {
                popUpTo("teacher_schedule/$name") {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }
    )
}