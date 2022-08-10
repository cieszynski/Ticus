package de.cieszynski.ticus.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import de.cieszynski.ticus.TicusApplication
import de.cieszynski.ticus.data.AppContainer
import de.cieszynski.ticus.ui.navigation.NavBar
import de.cieszynski.ticus.ui.navigation.NavHostMain
import de.cieszynski.ticus.ui.navigation.NavRail
import de.cieszynski.ticus.ui.theme.TicusTheme

@Composable
fun TicusApp(
    appContainer: AppContainer,
    windowSizeClass: WindowSizeClass
) {
    TicusTheme {
        val isCompact = windowSizeClass.heightSizeClass == WindowHeightSizeClass.Compact
                || windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact
        val navController = rememberNavController()

        if (isCompact) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    NavHostMain(
                        appContainer = appContainer,
                        navController = navController,
                        isCompact = isCompact
                    )
                }
                NavBar(navController = navController)
            }
        } else {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Start
            ) {
                NavRail(navController = navController)
                Box(modifier = Modifier.weight(1f)) {
                    NavHostMain(
                        appContainer = appContainer,
                        navController = navController,
                        isCompact = isCompact
                    )
                }
            }
        }
    }
}

@Composable
fun Home() {
    Text("Home 2")
}

@Composable
fun Favorites() {
    Text("Favorites 2")
}

/*
@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun DefaultPreview() {
    val appContainer = (application as TicusApplication).container
    TicusApp(
        appContainer = appContainer,
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
*/