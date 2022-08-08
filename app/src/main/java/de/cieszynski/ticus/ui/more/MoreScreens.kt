package de.cieszynski.ticus.ui.more

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import de.cieszynski.ticus.ui.navigation.NavHostMore
import de.cieszynski.ticus.ui.navigation.NavItem

@Composable
fun MoreHomeScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {

        Text("MoreHome-1")
        Button(onClick = {
            navController.navigate(NavItem.MoreImprint.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }) {
            Text(text = "Impressum")
        }
        Button(onClick = {
            navController.navigate(NavItem.MoreSettings.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        }) {
            Text(text = "Einstellungen")
        }
    }
}

@Composable
fun MoreSettingsScreen() {
    Text("MoreSettings-2")
}

@Composable
fun MoreImprintScreen() {
    Text("MoreImprint-2")
}

@Composable
fun MoreScreen(isCompact: Boolean) {

    val navController = rememberNavController()

    if (isCompact) {
        NavHostMore(navController = navController)
    } else {
        Row(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.weight(1f)) {
                MoreHomeScreen(navController = navController)
            }
            Box(modifier = Modifier.weight(1f)) {
                NavHostMore(
                    navController = navController,
                    startDestination = NavItem.MoreImprint.route
                )
            }
        }
    }
}