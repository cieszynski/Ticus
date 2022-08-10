package de.cieszynski.ticus.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import de.cieszynski.ticus.data.AppContainer
import de.cieszynski.ticus.ui.Favorites
import de.cieszynski.ticus.ui.more.MoreScreen

@Composable
fun NavHostMain(
    appContainer: AppContainer,
    navController: NavHostController,
    isCompact: Boolean
) {
    NavHost(
        navController = navController,
        startDestination = NavItem.Home.route,
    ) {
        composable(NavItem.Home.route) {
            NavHostHome(appContainer = appContainer, isCompact = isCompact)
        }

        composable(NavItem.Favorites.route) {
            Favorites()
        }

        composable(NavItem.More.route) {
            MoreScreen(isCompact = isCompact)
        }
    }
}