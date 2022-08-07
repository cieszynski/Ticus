package de.cieszynski.ticus.ui.more

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import de.cieszynski.ticus.R

sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object Welcome : NavRoutes("welcome")
    object Profile : NavRoutes("profile")
}

@Composable
fun MoreScreen(isCompact: Boolean) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route,
    ) {
        composable(NavRoutes.Home.route) {
            Home(navController = navController)
        }

        composable(NavRoutes.Welcome.route) {
            Welcome(navController = navController)
        }

        composable(NavRoutes.Profile.route) {
            Profile(navController = navController)
        }
    }

    if(isCompact) {
        MoreScreenCompact()
    } else {
        MoreScreenExpanded()
    }

}

@Composable
fun Home(navController: NavHostController) {

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Home Screen")
        Button(onClick = {
            navController.navigate(NavRoutes.Welcome.route)
        }) {
            Text(text = "Register")
        }
    }
}

@Composable
fun Profile(navController: NavHostController) {

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Profile Screen")
    }
}

@Composable
fun Welcome(navController: NavHostController) {

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Welcome")

            Spacer(modifier = Modifier.size(30.dp))

            Button(onClick = { }) {
                Text(text = "Set up your Profile")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreScreenCompact() {
    Column {
        CenterAlignedTopAppBar(
            title = { Text(stringResource(R.string.label_more)) },
//            navigationIcon = {
//                IconButton(onClick = { }) {
//                    Icon(
//                        imageVector = Icons.Filled.Menu,
//                        contentDescription = "Localized description",
//                    )
//                }
//            },
            actions = {
                IconButton(onClick = {  }) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Localized description",
                    )
                }
            },
        )
        Text(text = "MoreScreen isCompact")
    }
}

@Composable
fun MoreScreenExpanded() {
    Row {
        Text(text = "MoreScreen")
    }
}