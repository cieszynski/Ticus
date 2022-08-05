package de.cieszynski.ticus.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.ui.graphics.vector.ImageVector
import de.cieszynski.ticus.R

sealed class NavigationItem(
    val route: String,
    @StringRes val label: Int,
    val icon: ImageVector
) {
    object Home : NavigationItem("home", R.string.label_home, Icons.Outlined.Home)
    object Menu : NavigationItem("menu", R.string.label_menu, Icons.Outlined.Menu)
}