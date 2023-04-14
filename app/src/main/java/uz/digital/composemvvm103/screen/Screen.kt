package uz.digital.composemvvm103.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    object Home: Screen(
        route = "home_screen",
        label = "Home",
        icon = Icons.Default.Home
    )
    object Favorite: Screen(
        route = "favorite_screen",
        label = "Favorite",
        icon = Icons.Default.Favorite
    )
    object Settings: Screen(
        route = "settings_screen",
        label = "Settings",
        icon = Icons.Default.Settings
    )
}
