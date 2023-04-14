package uz.digital.composemvvm103

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import uz.digital.composemvvm103.screen.Screen
import uz.digital.composemvvm103.ui.theme.ComposeMVVM103Theme
import uz.digital.composemvvm103.ui.theme.FavoriteScreen
import uz.digital.composemvvm103.ui.theme.HomeScreen
import uz.digital.composemvvm103.ui.theme.SettingScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMVVM103Theme(

            ) {
                val navHostController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                val scope = rememberCoroutineScope()
                Scaffold(
                    scaffoldState = scaffoldState,
                    topBar = {
                        TopAppBar(
                            navigationIcon = {
                                IconButton(onClick = {
                                    scope.launch {
                                        scaffoldState.drawerState.open()
                                    }
                                }) {
                                    Icon(imageVector = Icons.Default.Menu, contentDescription = "")
                                }
                            },
                            title = {

                            }
                        )
                    },
                    bottomBar = {
                        BottomScreen(navHostController = navHostController)
                    },
                    drawerContent = {
                        Text(text = "Navigation Drawer")
                    },
//                    floatingActionButton = {
//                        FloatingActionButton(onClick = { /*TODO*/ }) {
//
//                        }
//                    }
                ) {
                    NavHost(
                        navController = navHostController,
                        startDestination = Screen.Home.route
                    ) {
                        composable(route = Screen.Home.route) {
                            HomeScreen()
                        }
                        composable(route = Screen.Favorite.route) {
                            FavoriteScreen()
                        }
                        composable(route = Screen.Settings.route) {
                            SettingScreen()
                        }
                    }
                }
                BackHandler() {
                    if (scaffoldState.drawerState.isOpen) {
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                    } else {
                        this.finish()
                    }
                }
            }
        }
    }
}

