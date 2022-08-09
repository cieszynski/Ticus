package de.cieszynski.ticus.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.cieszynski.ticus.ui.home.HomeRoute
import de.cieszynski.ticus.ui.home.HomeViewModel

@Composable
fun NavHostHome(
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavItem.HomeRoute.route,
    isCompact: Boolean
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        composable(NavItem.HomeRoute.route) {
            val homeViewModel: HomeViewModel = viewModel()
            HomeRoute(homeViewModel=homeViewModel,isCompact = isCompact)
        }
    }
}