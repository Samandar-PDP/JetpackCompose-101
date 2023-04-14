package uz.digital.composemvvm103

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import uz.digital.composemvvm103.screen.Screen

@Composable
fun BottomScreen(navHostController: NavHostController) {
    val screens = listOf(
        Screen.Home,
        Screen.Favorite,
        Screen.Settings,
    )
    val navBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarScreen = screens.any { it.route == currentDestination?.route }

    if (bottomBarScreen) {
        BottomNavigation() {
            screens.forEach { screen ->
                BottomNavigationItem(
                    selected = currentDestination?.hierarchy?.any {
                        it.route == screen.route
                    } == true,
                    onClick = {
                        navHostController.navigate(screen.route) {
                            popUpTo(navHostController.graph.findStartDestination().id)
                            launchSingleTop = true
                        }
                    },
                    label = {
                        Text(text = screen.label)
                    },
                    icon = {
                        Icon(imageVector = screen.icon, contentDescription = "icon")
                    },
                    selectedContentColor = Color.Green,
                    unselectedContentColor = Color.Gray
                )
            }
        }
    }
}