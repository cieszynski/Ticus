package de.cieszynski.ticus.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import de.cieszynski.ticus.ui.more.MoreScreen
import de.cieszynski.ticus.ui.theme.TicusTheme

sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object Favorites : NavRoutes("favorites")
    object More : NavRoutes("more")
}

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route,
    ) {
        composable(NavRoutes.Home.route) {
            Home()
        }

        composable(NavRoutes.Favorites.route) {
            Favorites()
        }

        composable(NavRoutes.More.route) {
            MoreScreen(isCompact = true)
        }
    }
}

// https://github.com/android/compose-samples/blob/main/JetNews/app/src/main/java/com/example/jetnews/ui/JetnewsApp.kt
@Composable
fun TicusApp(windowSizeClass: WindowSizeClass) {
    TicusTheme {
        val isCompact = windowSizeClass.heightSizeClass == WindowHeightSizeClass.Compact
                || windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact
        val navController = rememberNavController()

        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route

        val navigationItems =
            listOf(
                NavigationItem.Home,
                NavigationItem.Fav,
                NavigationItem.More
            )

        if (isCompact) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    NavigationHost(navController = navController)
                }
                NavigationBar {
                    NavigationBarItem(
                        icon = { Icon(imageVector = Icons.Outlined.Home, contentDescription = "") },
                        label = { Text("Home") },
                        selected = currentRoute == NavRoutes.Home.route,
                        onClick = {
                            navController.navigate(NavRoutes.Home.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        })
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.Favorite,
                                contentDescription = ""
                            )
                        },
                        label = { Text("Favorites") },
                        selected = currentRoute == NavRoutes.Favorites.route,
                        onClick = {
                            navController.navigate(NavRoutes.Favorites.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        })
                    NavigationBarItem(
                        icon = {
                            Icon(
                                imageVector = Icons.Outlined.MoreVert,
                                contentDescription = ""
                            )
                        },
                        label = { Text("More") },
                        selected = currentRoute == NavRoutes.More.route,
                        onClick = {
                            navController.navigate(NavRoutes.More.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        })
                }
            }
        }
    }

    /*
    val selectedIndex = rememberSaveable { mutableStateOf(0) }

    if (isCompact) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
            ) {
                NavPane(
                    isCompact = isCompact,
                    selectedIndex = selectedIndex
                )
            }
            NavBar(items = navigationItems, selectedIndex = selectedIndex)
        }
    } else {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
        ) {
            NavRail(items = navigationItems, selectedIndex = selectedIndex)
            Box(modifier = Modifier
                .weight(1f)
                .fillMaxSize()) {
                NavPane(
                    isCompact = isCompact,
                    selectedIndex = selectedIndex
                )
            }
        }
    }*/
/*
        Scaffold(
            topBar = {
                if (isCompact) {
                    SmallTopAppBar(
                        title = { Text("Simple TopAppBar") },
                        navigationIcon = {
                            IconButton(onClick = { }) {
                                Icon(
                                    imageVector = Icons.Filled.Menu,
                                    contentDescription = "Localized description",
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = {  }) {
                                Icon(
                                    imageVector = Icons.Filled.Favorite,
                                    contentDescription = "Localized description",
                                )
                            }
                        },
                    )
                }
            },
            bottomBar = {
                if (isCompact) {
                    NavigationBar {
                        navigationItem.forEachIndexed { index, item ->
                            NavigationBarItem(
                                icon = { Icon(imageVector = item.icon, contentDescription = "") },
                                label = { Text(stringResource(item.label)) },
                                selected = selectedIndex.value == index,
                                onClick = { selectedIndex.value = index}
                            )
                        }
                    }
                }
            },
            content = { innerPadding ->
                Row(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .wrapContentSize()
                ) {
                    if (!isCompact) {
                        TicusNavRail {
                            navigationItem.forEachIndexed { index, item ->
                                NavigationRailItem(
                                    icon = { Icon(imageVector = item.icon, contentDescription = "") },
                                    label = { Text(stringResource(item.label)) },
                                    selected = selectedIndex.value == index,
                                    onClick = { selectedIndex.value = index}
                                )
                            }
                        }
                    }
                    Text(
                        text = "Body content",
                        modifier = Modifier
                            .weight(1f)
                    )
                }
            })*/
}

@Composable
fun Home() {
    Text("Home 2")
}

@Composable
fun Favorites() {
    Text("Favorites 2")
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun DefaultPreview() {
    //CompactScreen { Text(text = "OK2")}
    TicusApp(
        windowSizeClass = WindowSizeClass.calculateFromSize(
            size = DpSize(
                width = 360.dp,
                height = 640.dp
            )
        )
    )
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, widthDp = 640, heightDp = 360)
@Composable
fun DefaultPreview2() {
    //CompactScreen { Text(text = "OK2")}
    TicusApp(
        windowSizeClass = WindowSizeClass.calculateFromSize(
            size = DpSize(
                width = 640.dp,
                height = 360.dp
            )
        )
    )
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, name = "Medium", widthDp = 840, heightDp = 640)
@Composable
fun MediumPreview() {
    TicusApp(
        windowSizeClass = WindowSizeClass.calculateFromSize(
            size = DpSize(
                width = 840.dp,
                height = 640.dp
            )
        )
    )
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, name = "Expanded", widthDp = 1200, heightDp = 840)
@Composable
fun ExpandedPreview() {
    TicusApp(
        windowSizeClass = WindowSizeClass.calculateFromSize(
            size = DpSize(
                width = 1200.dp,
                height = 840.dp
            )
        )
    )
}