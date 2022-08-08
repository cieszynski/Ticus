package de.cieszynski.ticus.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.ui.graphics.vector.ImageVector
import de.cieszynski.ticus.R

sealed class NavItem(
    val route: String,
    @StringRes val label: Int,
    val icon: ImageVector
) {
    object Home : NavItem("home", R.string.label_home, Icons.Outlined.Home)
    object Favorites : NavItem("favorites", R.string.label_favorites, Icons.Outlined.Favorite)
    object More : NavItem("more", R.string.label_more, Icons.Outlined.MoreVert)
}

val navigationItems =
    listOf(
        NavItem.Home,
        NavItem.Favorites,
        NavItem.More
    )
