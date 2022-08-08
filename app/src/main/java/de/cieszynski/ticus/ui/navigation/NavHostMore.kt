package de.cieszynski.ticus.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import de.cieszynski.ticus.ui.more.MoreHomeScreen
import de.cieszynski.ticus.ui.more.MoreImprintScreen
import de.cieszynski.ticus.ui.more.MoreSettingsScreen

@Composable
fun NavHostMore(navController: NavHostController, startDestination:String = NavItem.MoreHome.route) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(NavItem.MoreHome.route) {
            MoreHomeScreen(navController = navController)
        }
        composable(NavItem.MoreImprint.route) {
            MoreImprintScreen()
        }
        composable(NavItem.MoreSettings.route) {
            MoreSettingsScreen()
        }
    }
}