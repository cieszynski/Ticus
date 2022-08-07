package de.cieszynski.ticus.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.cieszynski.ticus.ui.home.HomeScreen
import de.cieszynski.ticus.ui.more.Home
import de.cieszynski.ticus.ui.more.MoreScreen
import de.cieszynski.ticus.ui.more.Profile
import de.cieszynski.ticus.ui.more.Welcome

sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object Welcome : NavRoutes("welcome")
    object More : NavRoutes("more")
}

@Composable
fun NavPane(selectedIndex: MutableState<Int>, isCompact: Boolean) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route,
    ) {
        composable(NavRoutes.Home.route) {
            Home()
        }

        composable(NavRoutes.Welcome.route) {
            Welcome()
        }

        composable(NavRoutes.More.route) {
            MoreScreen(isCompact = isCompact)
        }
    }

    when (selectedIndex.value) {
        1 -> navController.navigate(NavRoutes.Welcome.route)
        2 -> navController.navigate(NavRoutes.More.route)
        else -> navController.navigate(NavRoutes.Home.route)
    }
//    when (selectedIndex.value) {
//        1 -> Text(text = "OK Fav")
//        2 -> MoreScreen(isCompact = isCompact)
//        else -> HomeScreen(isCompact = isCompact)
//    }
}

@Composable
fun Home() {
    Text("Home 1")
}

@Composable
fun Welcome() {
    Text("Welcome 1")
}

@Composable
fun More() {
    Text("More 1")
}
